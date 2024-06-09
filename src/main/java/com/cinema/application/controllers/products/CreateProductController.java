package com.cinema.application.controllers.products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.products.CreateProductInfosDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.entities.products.ProductInfos;
import com.cinema.domain.errors.products.ProductAlreadyExistsError;
import com.cinema.domain.usecases.products.CreateInventoryUseCase;
import com.cinema.domain.usecases.products.CreateProductInfosUseCase;

public class CreateProductController extends Controller<CreateProductInfosDTO> {
  private CreateProductInfosUseCase createProductUseCase;
  private CreateInventoryUseCase createInventoryUseCase;

  public CreateProductController(CreateProductInfosUseCase createProductUseCase,
      CreateInventoryUseCase createInventoryUseCase) {
    this.createProductUseCase = createProductUseCase;
    this.createInventoryUseCase = createInventoryUseCase;
  }

  @Override
  public Response<?> perform(CreateProductInfosDTO object) {
    try {
      UUID productUUID = this.createProductUseCase.execute(
          object.getName(),
          object.getPrice());

      ProductInfos product = new ProductInfos(productUUID, object.getName(), object.getPrice());
      this.createInventoryUseCase.execute(product, object.getQuantity());

      return ResponseFactory.noContent();
    } catch (ProductAlreadyExistsError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(CreateProductInfosDTO object) {
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
