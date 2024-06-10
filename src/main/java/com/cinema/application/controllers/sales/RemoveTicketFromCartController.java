package com.cinema.application.controllers.sales;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.sales.RemoveTicketFromCartDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.errors.sale.TicketNotFoundError;
import com.cinema.domain.usecases.sale.RemoveTicketFromCartUseCase;

/**
 * Controller class for removing a ticket from the cart.
 */
public class RemoveTicketFromCartController extends Controller<RemoveTicketFromCartDTO> {
  private RemoveTicketFromCartUseCase removeTicketFromCartUseCase;

  /**
   * Constructs a new RemoveTicketFromCartController with the specified RemoveTicketFromCartUseCase.
   *
   * @param removeTicketFromCartUseCase the RemoveTicketFromCartUseCase to be used
   */
  public RemoveTicketFromCartController(RemoveTicketFromCartUseCase removeTicketFromCartUseCase) {
    this.removeTicketFromCartUseCase = removeTicketFromCartUseCase;
  }

  /**
   * Performs the removal of a ticket from the cart.
   *
   * @param removeTicketFromCartDTO the RemoveTicketFromCartDTO containing the ticket ID to be removed
   * @return a Response object indicating the result of the removal operation
   */
  public Response<?> perform(RemoveTicketFromCartDTO removeTicketFromCartDTO) {
    try {
      removeTicketFromCartUseCase.execute(removeTicketFromCartDTO.getID());

      return ResponseFactory.noContent();
    } catch (TicketNotFoundError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds the list of validators for the RemoveTicketFromCartDTO object.
   *
   * @param object the RemoveTicketFromCartDTO object
   * @return an ArrayList of IValidator objects
   */
  public ArrayList<IValidator> buildValidators(RemoveTicketFromCartDTO object) {
    return new ArrayList<IValidator>();
  }
}
