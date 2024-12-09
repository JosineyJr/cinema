package com.cinema.main.factories.sales;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.sales.ListSalesController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.sale.ListSalesUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.sale.PgSaleRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class ListSalesFactory {
  public static Controller<Object> make() {
    PgSaleRepository pgSaleRepository = new PgSaleRepository();

    ListSalesUseCase listSalesUseCase = new ListSalesUseCase(pgSaleRepository);

    ListSalesController listSalesController = new ListSalesController(listSalesUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(listSalesController, pgConnection);
  }
}
