package com.cinema.application.controllers.products;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.products.CreateProductDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.entities.products.Product;
import com.cinema.domain.errors.products.ProductAlreadyExistsError;
import com.cinema.domain.usecases.products.CreateInventoryUseCase;
import com.cinema.domain.usecases.products.CreateProductUseCase;

/**
 * Controller class for creating a product.
 * This class extends the base Controller class and handles the creation of a
 * product and its inventory.
 */
public class CreateProductController extends Controller<CreateProductDTO> {
  private CreateProductUseCase createProductUseCase;
  private CreateInventoryUseCase createInventoryUseCase;

  public CreateProductController(CreateProductUseCase createProductUseCase,
      CreateInventoryUseCase createInventoryUseCase) {
    this.createProductUseCase = createProductUseCase;
    this.createInventoryUseCase = createInventoryUseCase;
  }

  /**
   * Performs the creation of a new product and updates the inventory.
   *
   * @param object The CreateProductDTO object containing the product
   *               information.
   * @return A Response object indicating the success or failure of the operation.
   */
  @Override
  public Response<?> perform(CreateProductDTO object) {
    try {
      UUID productUUID = this.createProductUseCase.execute(
          object.getName(),
          object.getPrice());

      Product product = new Product(productUUID, object.getName(), object.getPrice());
      this.createInventoryUseCase.execute(product, object.getQuantity());

      return ResponseFactory.noContent();
    } catch (ProductAlreadyExistsError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given CreateProductDTO
   * object.
   * 
   * @param object The CreateProductDTO object to validate.
   * @return An ArrayList of IValidator objects representing the validators for
   *         the given object.
   */
  @Override
  public ArrayList<IValidator> buildValidators(CreateProductDTO object) {
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
