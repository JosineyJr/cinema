package com.cinema.main.factories.products;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.products.ListProductController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.products.ListInventoryUseCase;
import com.cinema.domain.usecases.products.ListProductsUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgInventoryRepository;
import com.cinema.infra.db.postgres.repositores.products.PgProductRepository;

public class ListProductsFactory {
  public static Controller<Object> make() {
    PgProductRepository productRepository = new PgProductRepository();
    PgInventoryRepository inventoryRepository = new PgInventoryRepository();

    ListProductsUseCase listProductsUseCase = new ListProductsUseCase(productRepository);
    ListInventoryUseCase listInventoryUseCase = new ListInventoryUseCase(inventoryRepository);

    ListProductController listProductController = new ListProductController(listProductsUseCase, listInventoryUseCase);

    PgConnection pgConnection = PgConnection.getInstance();

    return new DbTransactionController<>(listProductController, pgConnection);
  }
}
