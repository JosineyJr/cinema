package com.cinema.main.factories.sales;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.sales.RemoveProductCartFromCartController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.sales.RemoveProductFromCartDTO;
import com.cinema.domain.usecases.sale.RemoveProductCartFromCartUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.sale.PgProductCartRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class RemoveProductFromCartFactory {
  public static Controller<RemoveProductFromCartDTO> make() {
    PgProductCartRepository pgProductRepository = new PgProductCartRepository();

    RemoveProductCartFromCartUseCase removeProductFromCartUseCase = new RemoveProductCartFromCartUseCase(
        pgProductRepository,
        pgProductRepository);

    RemoveProductCartFromCartController removeProductFromCartController = new RemoveProductCartFromCartController(
        removeProductFromCartUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(removeProductFromCartController, pgConnection);
  }
}
