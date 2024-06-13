package com.cinema.application.controllers.sales;

import java.util.ArrayList;
import java.util.UUID;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.sales.RemoveTicketCartFromCartDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.errors.sale.TicketCartNotFoundError;
import com.cinema.domain.usecases.sale.RemoveTicketCartFromCartUseCase;

/**
 * Controller class for removing a ticket from the cart.
 */
public class RemoveTicketCartFromCartController extends Controller<RemoveTicketCartFromCartDTO> {
  private RemoveTicketCartFromCartUseCase removeTicketCartFromCartUseCase;

  /**
   * Constructs a new RemoveTicketFromCartController with the specified
   * RemoveTicketFromCartUseCase.
   *
   * @param removeTicketFromCartUseCase the RemoveTicketFromCartUseCase to be used
   */
  public RemoveTicketCartFromCartController(RemoveTicketCartFromCartUseCase removeTicketCartFromCartUseCase) {
    this.removeTicketCartFromCartUseCase = removeTicketCartFromCartUseCase;
  }

  /**
   * Performs the removal of a ticket from the cart.
   *
   * @param removeTicketFromCartDTO the RemoveTicketFromCartDTO containing the
   *                                ticket ID to be removed
   * @return a Response object indicating the result of the removal operation
   */
  public Response<?> perform(RemoveTicketCartFromCartDTO removeTicketCartFromCartDTO) {
    try {
      UUID ticketCartID = removeTicketCartFromCartDTO.getID();

      removeTicketCartFromCartUseCase.execute(ticketCartID);

      return ResponseFactory.noContent();
    } catch (TicketCartNotFoundError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds the list of validators for the RemoveTicketFromCartDTO object.
   *
   * @param object the RemoveTicketFromCartDTO object
   * @return an ArrayList of IValidator objects
   */
  public ArrayList<IValidator> buildValidators(RemoveTicketCartFromCartDTO object) {
    return new ArrayList<IValidator>();
  }
}
