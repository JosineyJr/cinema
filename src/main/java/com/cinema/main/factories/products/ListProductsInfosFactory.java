package com.cinema.main.factories.products;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.products.ListProductInfosController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.products.ListInventoryUseCase;
import com.cinema.domain.usecases.products.ListProductsInfosUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgInventoryRepository;
import com.cinema.infra.db.postgres.repositores.products.PgProductInfosRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class ListProductsInfosFactory {
  /**
   * Creates a Controller object for listing product information.
   * 
   * @return The created Controller object.
   */
  public static Controller<Object> make() {
    PgProductInfosRepository productInfosRepository = new PgProductInfosRepository();
    PgInventoryRepository inventoryRepository = new PgInventoryRepository();

    ListProductsInfosUseCase listProductsInfosUseCase = new ListProductsInfosUseCase(productInfosRepository);
    ListInventoryUseCase listInventoryUseCase = new ListInventoryUseCase(inventoryRepository);

    ListProductInfosController listProductController = new ListProductInfosController(
        listProductsInfosUseCase,
        listInventoryUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(listProductController, pgConnection);
  }
}
