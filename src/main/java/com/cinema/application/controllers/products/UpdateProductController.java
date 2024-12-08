package com.cinema.application.controllers.products;

import java.util.ArrayList;
import java.util.Arrays;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.products.EditProductDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.domain.entities.products.Product;
import com.cinema.domain.usecases.products.UpdateInventoryUseCase;
import com.cinema.domain.usecases.products.UpdateProductUseCase;

/**
 * Controller class for updating product information.
 * This class extends the base Controller class and handles the logic for
 * updating product information and inventory.
 */
public class UpdateProductController extends Controller<EditProductDTO> {
  private UpdateProductUseCase updateProductUseCase;
  private UpdateInventoryUseCase updateInventoryUseCase;

  public UpdateProductController(UpdateProductUseCase updateProductUseCase,
      UpdateInventoryUseCase updateInventoryUseCase) {
    this.updateProductUseCase = updateProductUseCase;
    this.updateInventoryUseCase = updateInventoryUseCase;
  }

  /**
   * Performs the update of a product and its inventory in the system.
   *
   * @param object The EditProductDTO object containing the updated product
   *               information.
   * @return A Response object indicating the success or failure of the update
   *         operation.
   */
  public Response<?> perform(EditProductDTO object) {
    try {
      System.out.println(object.getID());
      Product product = new Product(object.getID(), object.getName(), object.getPrice());
      Inventory inventory = new Inventory(object.getInventoryID(), product, object.getQuantity());

      updateProductUseCase.execute(product);
      updateInventoryUseCase.execute(inventory);

      return ResponseFactory.noContent();
    } catch (Exception e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given EditProductDTO
   * object.
   *
   * @param object The EditProductDTO object containing the product
   *               information to be validated.
   * @return An ArrayList of IValidator objects representing the validators for
   *         the product information.
   */
  public ArrayList<IValidator> buildValidators(EditProductDTO object) {
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
