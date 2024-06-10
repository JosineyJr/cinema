package com.cinema.application.controllers.sales;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.sales.ListCartDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.sale.Cart;
import com.cinema.domain.errors.sale.CartNotFoundError;
import com.cinema.domain.usecases.sale.ListPersonCartUseCase;

/**
 * The controller class for listing a person's cart.
 */
public class ListPersonCartController extends Controller<ListCartDTO> {
  private ListPersonCartUseCase listPersonCartUseCase;

  /**
   * Constructs a new ListPersonCartController with the specified ListPersonCartUseCase.
   *
   * @param listPersonCartUseCase the use case for listing a person's cart
   */
  public ListPersonCartController(ListPersonCartUseCase listPersonCartUseCase) {
    this.listPersonCartUseCase = listPersonCartUseCase;
  }

  /**
   * Performs the action of listing a person's cart.
   *
   * @param listCartDTO the DTO containing the person's ID
   * @return a Response object containing the cart or an error message
   */
  public Response<?> perform(ListCartDTO listCartDTO) {
    try {
      Cart cart = this.listPersonCartUseCase.execute(listCartDTO.getPersonID());

      return ResponseFactory.ok(cart);
    } catch (CartNotFoundError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given object.
   *
   * @param object the object for which validators need to be built
   * @return an ArrayList of IValidator objects
   */
  public ArrayList<IValidator> buildValidators(ListCartDTO object) {
    return new ArrayList<IValidator>();
  }
}
