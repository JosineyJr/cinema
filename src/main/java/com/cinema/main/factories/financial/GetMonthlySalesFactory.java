package com.cinema.main.factories.financial;

import com.cinema.application.controllers.Controller;
import com.cinema.application.controllers.financial.GetMonthlySalesController;
import com.cinema.application.decorators.DbTransactionController;
import com.cinema.domain.usecases.financial.GetMonthlySalesReportUseCase;
import com.cinema.infra.db.postgres.helpers.PgConnection;
import com.cinema.infra.db.postgres.repositores.sale.PgSaleRepository;
import com.cinema.main.factories.db.PgConnectionFactory;

public class GetMonthlySalesFactory {
  public static Controller<Object> make(){
    PgSaleRepository pgSaleRepository = new PgSaleRepository();

    GetMonthlySalesReportUseCase getMonthlySalesReportUseCase = new GetMonthlySalesReportUseCase(pgSaleRepository);

    GetMonthlySalesController getMonthlySalesController = new GetMonthlySalesController(getMonthlySalesReportUseCase);

    PgConnection pgConnection = PgConnectionFactory.make();

    return new DbTransactionController<>(getMonthlySalesController, pgConnection);
  }
}
