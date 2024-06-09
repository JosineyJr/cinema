package com.cinema.application.controllers.sales;

import java.util.ArrayList;
import java.util.UUID;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.sales.AddProductToCartDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.errors.products.ProductInfosNotFoundError;
import com.cinema.domain.errors.sale.AllProductsSoldError;
import com.cinema.domain.usecases.sale.AddProductToCartUseCase;

public class AddProductToCartController extends Controller<AddProductToCartDTO> {
  private AddProductToCartUseCase addProductToCartUseCase;

  public AddProductToCartController(AddProductToCartUseCase addProductToCartUseCase) {
    this.addProductToCartUseCase = addProductToCartUseCase;
  }

  @Override
  public Response<?> perform(AddProductToCartDTO object) {
    try {
      UUID productInfoID = UUID.fromString(object.getProductInfoID());

      UUID personID = UUID.fromString(object.getPersonID());

      this.addProductToCartUseCase.execute(productInfoID, personID);

      return ResponseFactory.noContent();
    } catch (ProductInfosNotFoundError | AllProductsSoldError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(AddProductToCartDTO object) {
    Field productInfoID = new Field(object.getProductInfoID(), "Informações do produto");
    Field personID = new Field(object.getPersonID(), "ID da pessoa");

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    validators.addAll(ValidationBuilder.of().validateUUID(productInfoID).validateUUID(personID).build());

    return validators;
  }

}
