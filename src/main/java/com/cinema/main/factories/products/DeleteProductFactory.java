package com.cinema.main.factories.products;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.products.DeleteProductController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.products.DeleteProductInfosDTO;
import com.cinema.domain.usecases.products.DeleteInventoryUseCase;
import com.cinema.domain.usecases.products.DeleteProductUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgInventoryRepository;
import com.cinema.infra.db.postgres.repositores.products.PgProductInfosRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class DeleteProductFactory {
  public static Controller<DeleteProductInfosDTO> make() {
    PgProductInfosRepository productRepository = new PgProductInfosRepository();
    PgInventoryRepository inventoryRepository = new PgInventoryRepository();

    DeleteProductUseCase deleteProductUseCase = new DeleteProductUseCase(productRepository, productRepository);
    DeleteInventoryUseCase deleteInventoryUseCase = new DeleteInventoryUseCase(inventoryRepository,
        inventoryRepository);

    DeleteProductController deleteProductController = new DeleteProductController(deleteProductUseCase,
        deleteInventoryUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(deleteProductController, pgConnection);
  }
}
