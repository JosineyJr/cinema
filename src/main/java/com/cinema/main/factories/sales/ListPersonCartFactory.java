package com.cinema.main.factories.sales;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.sales.ListPersonCartController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.sales.ListCartDTO;
import com.cinema.domain.usecases.sale.ListPersonCartUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.sale.PgCartRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class ListPersonCartFactory {
  public static Controller<ListCartDTO> make() {
    PgCartRepository pgCartRepository = new PgCartRepository();

    ListPersonCartUseCase listPersonCartUseCase = new ListPersonCartUseCase(pgCartRepository);

    ListPersonCartController listPersonCartController = new ListPersonCartController(listPersonCartUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(listPersonCartController, pgConnection);
  }
}
