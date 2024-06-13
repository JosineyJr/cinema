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
import com.cinema.domain.errors.products.ProductNotFoundError;
import com.cinema.domain.errors.sale.AllProductsSoldError;
import com.cinema.domain.usecases.sale.AddProductToCartUseCase;

/**
 * Controller class for adding a product to the cart.
 */
public class AddProductToCartController extends Controller<AddProductToCartDTO> {
  private AddProductToCartUseCase addProductToCartUseCase;

  /**
   * Constructs a new AddProductToCartController with the specified use case.
   * 
   * @param addProductToCartUseCase the use case for adding a product to the cart
   */
  public AddProductToCartController(AddProductToCartUseCase addProductToCartUseCase) {
    this.addProductToCartUseCase = addProductToCartUseCase;
  }

  /**
   * Performs the action of adding a product to the cart.
   * 
   * @param object the AddProductToCartDTO object containing the necessary data
   * @return a Response object indicating the result of the operation
   */
  @Override
  public Response<?> perform(AddProductToCartDTO object) {
    try {
      UUID productID = UUID.fromString(object.getProductID());

      UUID personID = UUID.fromString(object.getPersonID());

      this.addProductToCartUseCase.execute(productID, personID);

      return ResponseFactory.noContent();
    } catch (ProductNotFoundError | AllProductsSoldError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds the validators for the AddProductToCartDTO object.
   * 
   * @param object the AddProductToCartDTO object to be validated
   * @return an ArrayList of IValidator objects representing the validators
   */
  @Override
  public ArrayList<IValidator> buildValidators(AddProductToCartDTO object) {
    Field productID = new Field(object.getProductID(), "rmações do produto");
    Field personID = new Field(object.getPersonID(), "ID da pessoa");

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    validators.addAll(ValidationBuilder.of().validateUUID(productID).validateUUID(personID).build());

    return validators;
  }

}
