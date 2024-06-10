package com.cinema.application.controllers.sales;

import java.util.ArrayList;
import java.util.UUID;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.sales.AddTicketToCartDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.errors.products.TicketInfosNotFoundError;
import com.cinema.domain.errors.sale.AllTicketsSoldError;
import com.cinema.domain.usecases.sale.AddTicketToCartUseCase;

public class AddTicketToCartController extends Controller<AddTicketToCartDTO> {
  private AddTicketToCartUseCase addTicketToCartUseCase;

  public AddTicketToCartController(AddTicketToCartUseCase addTicketToCartUseCase) {
    this.addTicketToCartUseCase = addTicketToCartUseCase;
  }

  /**
   * Performs the operation of adding a ticket to the cart.
   *
   * @param object The AddTicketToCartDTO object containing the necessary
   *               information for adding a ticket to the cart.
   * @return A Response object indicating the success or failure of the operation.
   */
  @Override
  public Response<?> perform(AddTicketToCartDTO object) {
    try {

      UUID ticketInfoID = UUID.fromString(object.getTicketInfoID());
      UUID personID = UUID.fromString(object.getPersonID());

      this.addTicketToCartUseCase.execute(ticketInfoID, personID);

      return ResponseFactory.noContent();
    } catch (TicketInfosNotFoundError | AllTicketsSoldError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given AddTicketToCartDTO
   * object.
   *
   * @param object The AddTicketToCartDTO object for which validators need to be
   *               built.
   * @return An ArrayList of IValidator objects representing the validators.
   */
  @Override
  public ArrayList<IValidator> buildValidators(AddTicketToCartDTO object) {
    Field ticketInfoID = new Field(object.getTicketInfoID(), "Informações do ingresso");
    Field personID = new Field(object.getPersonID(), "ID da pessoa");

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    validators.addAll(ValidationBuilder.of().validateUUID(ticketInfoID).validateUUID(personID).build());

    return validators;
  }

}
