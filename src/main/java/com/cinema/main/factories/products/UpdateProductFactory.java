package com.cinema.main.factories.products;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.products.UpdateProductController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.products.EditProductDTO;
import com.cinema.domain.usecases.products.UpdateInventoryUseCase;
import com.cinema.domain.usecases.products.UpdateProductUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgInventoryRepository;
import com.cinema.infra.db.postgres.repositores.products.PgProductRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class UpdateProductFactory {
  /**
   * Creates a Controller instance for editing product information.
   *
   * @return The created Controller instance.
   */
  public static Controller<EditProductDTO> make() {
    PgProductRepository productRepository = new PgProductRepository();
    PgInventoryRepository inventoryRepository = new PgInventoryRepository();

    UpdateProductUseCase updateProductUseCase = new UpdateProductUseCase(productRepository);

    UpdateInventoryUseCase updateInventoryUseCase = new UpdateInventoryUseCase(inventoryRepository);

    UpdateProductController updateProductController = new UpdateProductController(updateProductUseCase,
        updateInventoryUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(updateProductController, pgConnection);
  }
}
