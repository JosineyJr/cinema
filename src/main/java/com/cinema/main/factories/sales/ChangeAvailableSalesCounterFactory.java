package com.cinema.main.factories.sales;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.sales.ChangeAvailableSalesCounterController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.application.dtos.sales.ChangeAvailableSalesCounterDTO;
import com.cinema.domain.usecases.sale.ChangeAvailableSalesCounterUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.sale.PgSalesCounterRepository;

public class ChangeAvailableSalesCounterFactory {
  public static Controller<ChangeAvailableSalesCounterDTO> make() {
    PgSalesCounterRepository pgSalesCounterRepository = new PgSalesCounterRepository();

    ChangeAvailableSalesCounterUseCase changeAvailableSalesCounterUseCase = new ChangeAvailableSalesCounterUseCase(
        pgSalesCounterRepository, pgSalesCounterRepository);

    ChangeAvailableSalesCounterController changeAvailableSalesCounterController = new ChangeAvailableSalesCounterController(
        changeAvailableSalesCounterUseCase);

    return new DbTransactionController<>(changeAvailableSalesCounterController, PgConnection.getInstance());
  }
}
