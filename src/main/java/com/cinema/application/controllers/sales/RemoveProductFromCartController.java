package com.cinema.application.controllers.sales;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.sales.RemoveProductFromCartDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.errors.sale.ProductNotFoundError;
import com.cinema.domain.usecases.sale.RemoveProductFromCartUseCase;

/**
 * Controller class for removing a product from the cart.
 */
public class RemoveProductFromCartController extends Controller<RemoveProductFromCartDTO> {
  private RemoveProductFromCartUseCase removeProductFromCartUseCase;

  /**
   * Constructs a new RemoveProductFromCartController with the specified RemoveProductFromCartUseCase.
   * 
   * @param removeProductFromCartUseCase the use case for removing a product from the cart
   */
  public RemoveProductFromCartController(RemoveProductFromCartUseCase removeProductFromCartUseCase) {
    this.removeProductFromCartUseCase = removeProductFromCartUseCase;
  }

  /**
   * Performs the removal of a product from the cart.
   * 
   * @param removeProductFromCartDTO the DTO containing the information of the product to be removed
   * @return a Response object indicating the result of the removal operation
   */
  public Response<?> perform(RemoveProductFromCartDTO removeProductFromCartDTO) {
    try {
      removeProductFromCartUseCase.execute(removeProductFromCartDTO.getID());

      return ResponseFactory.noContent();
    } catch (ProductNotFoundError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds the list of validators for the RemoveProductFromCartDTO object.
   * 
   * @param object the RemoveProductFromCartDTO object
   * @return an ArrayList of IValidator objects
   */
  public ArrayList<IValidator> buildValidators(RemoveProductFromCartDTO object) {
    return new ArrayList<IValidator>();
  }
}
