package com.cinema.main.factories.products;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.products.CreateProductController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.products.CreateProductInfosDTO;
import com.cinema.domain.usecases.products.CreateInventoryUseCase;
import com.cinema.domain.usecases.products.CreateProductInfosUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgInventoryRepository;
import com.cinema.infra.db.postgres.repositores.products.PgProductInfosRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class CreateProductFactory {
  /**
   * Creates a Controller object for creating product information.
   * 
   * @return the Controller object for creating product information.
   */
  public static Controller<CreateProductInfosDTO> make() {
    PgProductInfosRepository pgProductRepository = new PgProductInfosRepository();
    PgInventoryRepository pgInventoryRepository = new PgInventoryRepository();

    CreateProductInfosUseCase createProductUseCase = new CreateProductInfosUseCase(pgProductRepository,
        pgInventoryRepository,
        pgProductRepository);

    CreateInventoryUseCase createInventoryUseCase = new CreateInventoryUseCase(pgInventoryRepository);

    CreateProductController createProductController = new CreateProductController(createProductUseCase,
        createInventoryUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(createProductController, pgConnection);
  }
}
