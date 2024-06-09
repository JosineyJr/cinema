package com.cinema.application.controllers.products;

import java.util.ArrayList;
import java.util.Arrays;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.products.EditProductInfosDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.domain.entities.products.ProductInfos;
import com.cinema.domain.usecases.products.UpdateInventoryUseCase;
import com.cinema.domain.usecases.products.UpdateProductInfosUseCase;

public class UpdateProductController extends Controller<EditProductInfosDTO> {
  private UpdateProductInfosUseCase updateProductUseCase;
  private UpdateInventoryUseCase updateInventoryUseCase;

  public UpdateProductController(UpdateProductInfosUseCase updateProductUseCase,
      UpdateInventoryUseCase updateInventoryUseCase) {
    this.updateProductUseCase = updateProductUseCase;
    this.updateInventoryUseCase = updateInventoryUseCase;
  }

  public Response<?> perform(EditProductInfosDTO object) {
    try {
      System.out.println(object.getID());
      ProductInfos productInfos = new ProductInfos(object.getID(), object.getName(), object.getPrice());
      Inventory inventory = new Inventory(object.getInventoryID(), productInfos, object.getQuantity());

      updateProductUseCase.execute(productInfos);
      updateInventoryUseCase.execute(inventory);

      return ResponseFactory.noContent();
    } catch (Exception e) {
      return ResponseFactory.badRequest(e);
    }
  }

  public ArrayList<IValidator> buildValidators(EditProductInfosDTO object) {
    Field name = new Field(object.getName(), "Nome");
    Field price = new Field(String.valueOf(object.getPrice()), "Preço");
    Field quantity = new Field(String.valueOf(object.getQuantity()), "Quantidade");

    ArrayList<Field> requiredFields = new ArrayList<>(Arrays.asList(
        name,
        price,
        quantity));

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    validators.addAll(
        ValidationBuilder.of().required(requiredFields).minValue(quantity, 0).minDoubleValue(price, 0.0).build());

    return validators;
  }
}
