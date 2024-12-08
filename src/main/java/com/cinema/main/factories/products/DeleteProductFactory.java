package com.cinema.main.factories.products;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.products.DeleteProductController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.products.DeleteProductDTO;
import com.cinema.domain.usecases.products.DeleteInventoryUseCase;
import com.cinema.domain.usecases.products.DeleteProductUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgInventoryRepository;
import com.cinema.infra.db.postgres.repositores.products.PgProductRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class DeleteProductFactory {
  /**
   * Creates a Controller instance for deleting product information.
   * 
   * @return the created Controller instance.
   */
  public static Controller<DeleteProductDTO> make() {
    PgProductRepository productRepository = new PgProductRepository();
    PgInventoryRepository inventoryRepository = new PgInventoryRepository();

    DeleteProductUseCase deleteProductUseCase = new DeleteProductUseCase(productRepository,
        productRepository);
    DeleteInventoryUseCase deleteInventoryUseCase = new DeleteInventoryUseCase(inventoryRepository,
        inventoryRepository);

    DeleteProductController deleteProductController = new DeleteProductController(deleteProductUseCase,
        deleteInventoryUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(deleteProductController, pgConnection);
  }
}
