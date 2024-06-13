package com.cinema.application.controllers.sales;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.sales.RemoveProductFromCartDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.errors.sale.ProductCartNotFoundError;
import com.cinema.domain.usecases.sale.RemoveProductCartFromCartUseCase;

/**
 * Controller class for removing a product from the cart.
 */
public class RemoveProductCartFromCartController extends Controller<RemoveProductFromCartDTO> {
  private RemoveProductCartFromCartUseCase removeProductFromCartUseCase;

  /**
   * Constructs a new RemoveProductFromCartController with the specified
   * RemoveProductFromCartUseCase.
   * 
   * @param removeProductFromCartUseCase the use case for removing a product from
   *                                     the cart
   */
  public RemoveProductCartFromCartController(RemoveProductCartFromCartUseCase removeProductFromCartUseCase) {
    this.removeProductFromCartUseCase = removeProductFromCartUseCase;
  }

  /**
   * Performs the removal of a product from the cart.
   * 
   * @param removeProductFromCartDTO the DTO containing the information of the
   *                                 product to be removed
   * @return a Response object indicating the result of the removal operation
   */
  public Response<?> perform(RemoveProductFromCartDTO removeProductFromCartDTO) {
    try {
      removeProductFromCartUseCase.execute(removeProductFromCartDTO.getID());

      return ResponseFactory.noContent();
    } catch (ProductCartNotFoundError e) {
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
