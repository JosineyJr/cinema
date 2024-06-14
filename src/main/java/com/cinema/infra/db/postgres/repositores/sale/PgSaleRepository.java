package com.cinema.infra.db.postgres.repositores.sale;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.financial.IGetDailySalesReportRepository;
import com.cinema.domain.contracts.repositories.sale.ICreateSaleRepository;
import com.cinema.domain.contracts.repositories.sale.IListSalesRepository;
import com.cinema.domain.contracts.repositories.sale.IUpdateSaleRepository;
import com.cinema.domain.entities.financial.DailySalesReport;
import com.cinema.domain.entities.sale.Sale;
import com.cinema.domain.entities.sale.SalesCounter;
import com.cinema.infra.db.postgres.entities.sale.PgSale;
import com.cinema.infra.db.postgres.entities.sale.PgSalesCounter;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgSaleRepository
    extends PgRepository
    implements ICreateSaleRepository,
    IUpdateSaleRepository,
    IListSalesRepository,
    IGetDailySalesReportRepository {

  @Override
  public void updateSale(Sale sale) {
    PgSale pgSale = ConvertEntities.pgConvertSale(sale);

    this.session.merge(pgSale);
  }

  @Override
  public UUID createSale(Sale sale) {
    PgSale pgSale = ConvertEntities.pgConvertSale(sale);

    System.out.println("VENDA REALIZADA");

    this.session.persist(pgSale);

    return pgSale.getID();
  }

  public List<Sale> listSales() {
    List<PgSale> pgSales = this.session.createQuery("from sale", PgSale.class).list();

    return pgSales.stream().map(ConvertEntities::convertSale).toList();
  }

  public List<DailySalesReport> getDailySalesReport() {
    List<Object> report = this.session.createQuery(
        "SELECT      DATE(saleDate) AS sale_day,     salesCounter,     COUNT(id) AS total_sales,     SUM(totalPrice) AS total_price FROM      sale GROUP BY      DATE(saleDate),     salesCounter ORDER BY      sale_day,     salesCounter",
        Object.class).list();

    List<DailySalesReport> dailySalesReport = report.stream().map(this::convertToDailySalesReport).toList();
    return dailySalesReport;
  }

  private DailySalesReport convertToDailySalesReport(Object obj) {
    Object[] row = (Object[]) obj;

    java.sql.Date saleDaySqlDate = (java.sql.Date) row[0];
    LocalDate saleDay = saleDaySqlDate.toLocalDate();
    PgSalesCounter pgSalesCounter = (PgSalesCounter) row[1];
    SalesCounter salesCounter = ConvertEntities.convertSalesCounter(pgSalesCounter);

    return new DailySalesReport(
        saleDay,
        salesCounter,
        ((Number) row[2]).longValue(),
        ((Number) row[3]).doubleValue()
    );
  }
}
