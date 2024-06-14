package com.cinema.infra.db.postgres.repositores.sale;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.financial.IGetDailySalesReportRepository;
import com.cinema.domain.contracts.repositories.financial.IGetMonthlySalesReportRepository;
import com.cinema.domain.contracts.repositories.sale.ICreateSaleRepository;
import com.cinema.domain.contracts.repositories.sale.IListSalesRepository;
import com.cinema.domain.contracts.repositories.sale.IUpdateSaleRepository;
import com.cinema.domain.entities.financial.DailySalesReport;
import com.cinema.domain.entities.financial.MonthlySalesReport;
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
    IGetDailySalesReportRepository,
    IGetMonthlySalesReportRepository {

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
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    String hql = "SELECT DATE(saleDate) AS saleDay, salesCounter, COUNT(id) AS totalSales, SUM(totalPrice) AS totalPrice "
        +
        "FROM sale " +
        "WHERE TO_CHAR(saleDate, 'yyyy-MM-dd') = :today " +
        "GROUP BY DATE(saleDate), salesCounter " +
        "ORDER BY DATE(saleDate), salesCounter";

    List<Object[]> report = this.session.createQuery(hql, Object[].class)
        .setParameter("today", today.format(formatter))
        .list();

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
        ((Number) row[3]).doubleValue());
  }

  public List<MonthlySalesReport> getMonthlySalesReport() {
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

    String hql = "SELECT TO_CHAR(saleDate, 'yyyy-MM') AS saleMonth, salesCounter, COUNT(id) AS totalSales, SUM(totalPrice) AS totalPrice "
        +
        "FROM sale " +
        "WHERE TO_CHAR(saleDate, 'yyyy-MM') = :today " +
        "GROUP BY TO_CHAR(saleDate, 'yyyy-MM'), salesCounter " +
        "ORDER BY TO_CHAR(saleDate, 'yyyy-MM'), salesCounter";

    List<Object[]> report = this.session.createQuery(hql, Object[].class)
        .setParameter("today", today.format(formatter))
        .list();

    List<MonthlySalesReport> monthlySalesReport = report.stream().map(this::convertToMonthlySalesReport).toList();
    
    return monthlySalesReport;
  }

  private MonthlySalesReport convertToMonthlySalesReport(Object obj) {
    Object[] row = (Object[]) obj;

    PgSalesCounter pgSalesCounter = (PgSalesCounter) row[1];
    SalesCounter salesCounter = ConvertEntities.convertSalesCounter(pgSalesCounter);

    return new MonthlySalesReport(
        salesCounter,
        ((Number) row[2]).longValue(),
        ((Number) row[3]).doubleValue());
  }
}
