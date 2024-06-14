package com.cinema.main.factories.financial;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.financial.GetDailySalesController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.financial.GetDailySalesReportUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.sale.PgSaleRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class GetDailySalesFactory {
  public static Controller<Object> make() {
    PgSaleRepository pgSaleRepository = new PgSaleRepository();

    GetDailySalesReportUseCase getDailySalesReportUseCase = new GetDailySalesReportUseCase(pgSaleRepository);

    GetDailySalesController getDailySalesController = new GetDailySalesController(getDailySalesReportUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(getDailySalesController, pgConnection);
  }
}
