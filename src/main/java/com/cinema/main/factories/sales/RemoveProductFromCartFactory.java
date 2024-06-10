package com.cinema.main.factories.sales;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.sales.RemoveProductFromCartController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.sales.RemoveProductFromCartDTO;
import com.cinema.domain.usecases.sale.RemoveProductFromCartUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.products.PgProductRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class RemoveProductFromCartFactory {
  public static Controller<RemoveProductFromCartDTO> make() {
    PgProductRepository pgProductRepository = new PgProductRepository();

    RemoveProductFromCartUseCase removeProductFromCartUseCase = new RemoveProductFromCartUseCase(pgProductRepository,
        pgProductRepository);

    RemoveProductFromCartController removeProductFromCartController = new RemoveProductFromCartController(
        removeProductFromCartUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(removeProductFromCartController, pgConnection);
  }
}
