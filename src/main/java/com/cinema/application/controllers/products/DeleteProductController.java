package com.cinema.application.controllers.products;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.products.DeleteProductDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.usecases.products.DeleteInventoryUseCase;
import com.cinema.domain.usecases.products.DeleteProductUseCase;

public class DeleteProductController extends Controller<DeleteProductDTO> {
  private DeleteProductUseCase deleteProductUseCase;
  private DeleteInventoryUseCase deleteInventoryUseCase;

  public DeleteProductController(DeleteProductUseCase deleteProductUseCase,
      DeleteInventoryUseCase deleteInventoryUseCase) {
    this.deleteProductUseCase = deleteProductUseCase;
    this.deleteInventoryUseCase = deleteInventoryUseCase;
  }

  public Response<?> perform(DeleteProductDTO object) {
    try {
      deleteProductUseCase.deleteProduct(object.getProductId());
      deleteInventoryUseCase.deleteInventory(object.getInventoryId());

      return ResponseFactory.noContent();
    } catch (Exception e) {
      return ResponseFactory.badRequest(e);
    }
  }

  public ArrayList<IValidator> buildValidators(DeleteProductDTO object) {
    return new ArrayList<IValidator>();
  }
}
