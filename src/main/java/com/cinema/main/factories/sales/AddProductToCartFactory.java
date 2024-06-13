package com.cinema.main.factories.sales;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.sales.AddProductToCartController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.sales.AddProductToCartDTO;
import com.cinema.domain.usecases.sale.AddProductToCartUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgInventoryRepository;
import com.cinema.infra.db.postgres.repositores.products.PgProductRepository;
import com.cinema.infra.db.postgres.repositores.sale.PgCartRepository;
import com.cinema.infra.db.postgres.repositores.users.PgPersonRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class AddProductToCartFactory {
  public static Controller<AddProductToCartDTO> make() {
    PgProductRepository pgProductRepository = new PgProductRepository();

    PgCartRepository pgCartRepository = new PgCartRepository();

    PgPersonRepository pgPersonRepository = new PgPersonRepository();

    PgInventoryRepository pgInventoryRepository = new PgInventoryRepository();

    AddProductToCartUseCase addProductToCartUseCase = new AddProductToCartUseCase(pgProductRepository,
        pgCartRepository, pgPersonRepository, pgCartRepository, pgCartRepository, pgInventoryRepository);

    AddProductToCartController addProductToCartController = new AddProductToCartController(addProductToCartUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(addProductToCartController, pgConnection);
  }
}
