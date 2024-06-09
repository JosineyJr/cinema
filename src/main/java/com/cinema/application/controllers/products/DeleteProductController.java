package com.cinema.application.controllers.products;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.products.DeleteProductInfosDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.usecases.products.DeleteInventoryUseCase;
import com.cinema.domain.usecases.products.DeleteProductInfosUseCase;

public class DeleteProductController extends Controller<DeleteProductInfosDTO> {
  private DeleteProductInfosUseCase deleteProductUseCase;
  private DeleteInventoryUseCase deleteInventoryUseCase;

  public DeleteProductController(DeleteProductInfosUseCase deleteProductUseCase,
      DeleteInventoryUseCase deleteInventoryUseCase) {
    this.deleteProductUseCase = deleteProductUseCase;
    this.deleteInventoryUseCase = deleteInventoryUseCase;
  }

  /**
   * Performs the deletion of a product and its associated inventory.
   *
   * @param object The DeleteProductInfosDTO object containing the inventory and product IDs.
   * @return A Response object indicating the success or failure of the deletion operation.
   */
  public Response<?> perform(DeleteProductInfosDTO object) {
    try {
      deleteInventoryUseCase.execute(object.getInventoryId());
      deleteProductUseCase.execute(object.getProductId());

      return ResponseFactory.noContent();
    } catch (Exception e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given DeleteProductInfosDTO object.
   *
   * @param object The DeleteProductInfosDTO object for which validators need to be built.
   * @return An ArrayList of IValidator objects representing the validators for the given object.
   */
  public ArrayList<IValidator> buildValidators(DeleteProductInfosDTO object) {
    return new ArrayList<IValidator>();
  }
}
