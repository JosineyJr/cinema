package com.cinema.application.controllers.products;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.products.ProductInfosDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.domain.entities.products.ProductInfos;
import com.cinema.domain.usecases.products.ListInventoryUseCase;
import com.cinema.domain.usecases.products.ListProductsInfosUseCase;

public class ListProductInfosController extends Controller<Object> {
  private ListProductsInfosUseCase listProductsInfosUseCase;
  private ListInventoryUseCase listInventoryUseCase;

  public ListProductInfosController(ListProductsInfosUseCase listProductsInfosUseCase,
      ListInventoryUseCase listInventoryUseCase) {
    this.listProductsInfosUseCase = listProductsInfosUseCase;
    this.listInventoryUseCase = listInventoryUseCase;
  }

  /**
   * Performs the necessary operations to retrieve a list of product information and inventory details.
   * 
   * @param object An optional parameter (not used in this implementation).
   * @return A Response object containing a list of ProductInfosDTO.
   * @throws Exception if an error occurs during the execution.
   */
  public Response<?> perform(Object object) {
    try {
      List<ProductInfos> products = this.listProductsInfosUseCase.execute();
      List<Inventory> inventories = this.listInventoryUseCase.execute();

      List<ProductInfosDTO> productsDTO = new ArrayList<ProductInfosDTO>();

      for (ProductInfos product : products) {
        Inventory inventory = inventories.stream().filter(i -> i.getProductInfos().getID() == product.getID())
            .findFirst()
            .orElse(null);

        productsDTO
            .add(new ProductInfosDTO(product.getID(), product.getName(), product.getPrice(), inventory.getQuantity(),
                inventory.getID()));
      }

      return ResponseFactory.ok(productsDTO);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Builds and returns a list of validators for the given object.
   *
   * @param object the object for which validators need to be built
   * @return an ArrayList of IValidator objects
   */
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }
}
