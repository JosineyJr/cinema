package com.cinema.main.factories.products;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.products.ListProductController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.products.ListInventoryUseCase;
import com.cinema.domain.usecases.products.ListProductsUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgInventoryRepository;
import com.cinema.infra.db.postgres.repositores.products.PgProductRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class ListProductsFactory {
  /**
   * Creates a Controller object for listing product information.
   * 
   * @return The created Controller object.
   */
  public static Controller<Object> make() {
    PgProductRepository productRepository = new PgProductRepository();
    PgInventoryRepository inventoryRepository = new PgInventoryRepository();

    ListProductsUseCase listProductsUseCase = new ListProductsUseCase(productRepository);
    ListInventoryUseCase listInventoryUseCase = new ListInventoryUseCase(inventoryRepository);

    ListProductController listProductController = new ListProductController(
        listProductsUseCase,
        listInventoryUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(listProductController, pgConnection);
  }
}
