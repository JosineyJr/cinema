package com.cinema.main.factories.sales;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.sales.CreateOrListSalesCounterController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.sale.CreateOrListSalesCounterUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.sale.PgSalesCounterRepository;

public class CreateOrListSalesCounterFactory {
  public static Controller<Object> make() {
    PgSalesCounterRepository pgSalesCounterRepository = new PgSalesCounterRepository();

    CreateOrListSalesCounterUseCase createOrListSalesCounterUseCase = new CreateOrListSalesCounterUseCase(
        pgSalesCounterRepository, pgSalesCounterRepository);

    CreateOrListSalesCounterController createOrListSalesCounterController = new CreateOrListSalesCounterController(
        createOrListSalesCounterUseCase);

    PgConnection pgConnection = PgConnection.getInstance();

    return new DbTransactionController<>(createOrListSalesCounterController, pgConnection);
  }
}
