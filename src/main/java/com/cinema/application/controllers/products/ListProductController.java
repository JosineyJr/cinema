package com.cinema.application.controllers.products;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.products.ProductDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.domain.entities.products.Product;
import com.cinema.domain.usecases.products.ListInventoryUseCase;
import com.cinema.domain.usecases.products.ListProductsUseCase;

public class ListProductController extends Controller<Object> {
  private ListProductsUseCase listProductsUseCase;
  private ListInventoryUseCase listInventoryUseCase;

  public ListProductController(ListProductsUseCase listProductsUseCase, ListInventoryUseCase listInventoryUseCase) {
    this.listProductsUseCase = listProductsUseCase;
    this.listInventoryUseCase = listInventoryUseCase;
  }

  public Response<?> perform(Object object) {
    try {
      List<Product> products = this.listProductsUseCase.execute();
      List<Inventory> inventories = this.listInventoryUseCase.execute();

      List<ProductDTO> productsDTO = new ArrayList<ProductDTO>();

      for (Product product : products) {
        Inventory inventory = inventories.stream().filter(i -> i.getProduct().getID() == product.getID()).findFirst()
            .orElse(null);

        productsDTO.add(new ProductDTO(product.getID(), product.getName(), product.getPrice(), inventory.getQuantity()));
      }

      return ResponseFactory.ok(productsDTO);
    } catch (Exception e) {
      throw e;
    }
  }

  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }
}
