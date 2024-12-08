package com.cinema.infra.db.postgres.repositores.sale;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.sale.ICreateSalesCounterRepository;
import com.cinema.domain.contracts.repositories.sale.IFindSalesCounterByIDRepository;
import com.cinema.domain.contracts.repositories.sale.IListSalesCounterRepository;
import com.cinema.domain.contracts.repositories.sale.IUpdateSalesCounterRepository;
import com.cinema.domain.entities.sale.SalesCounter;
import com.cinema.infra.db.postgres.entities.sale.PgSale;
import com.cinema.infra.db.postgres.entities.sale.PgSalesCounter;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgSalesCounterRepository extends PgRepository
    implements IListSalesCounterRepository, ICreateSalesCounterRepository, IFindSalesCounterByIDRepository,
    IUpdateSalesCounterRepository {

  @Override
  public SalesCounter createSalesCounter(SalesCounter salesCounter) {
    PgSalesCounter pgSalesCounter = ConvertEntities.pgConvertSalesCounter(salesCounter);

    this.session.persist(pgSalesCounter);

    return ConvertEntities.convertSalesCounter(pgSalesCounter);
  }

  @Override
  public List<SalesCounter> listSalesCounter() {
    List<PgSalesCounter> pgSalesCounters = this.session
        .createQuery("from sales_counter", PgSalesCounter.class)
        .getResultList();

    return pgSalesCounters.stream().map(pgSalesCounter -> {
      return ConvertEntities.convertSalesCounter(pgSalesCounter);
    }).toList();
  }

  @Override
  public SalesCounter findByID(UUID ID) {
    PgSalesCounter pgSalesCounter = this.session.find(PgSalesCounter.class, ID);

    return ConvertEntities.convertSalesCounter(pgSalesCounter);
  }

  @Override
  public void update(SalesCounter salesCounter) {
    PgSalesCounter pgSalesCounter = ConvertEntities.pgConvertSalesCounter(salesCounter);

    List<PgSale> updatedSales = pgSalesCounter.getSales();
    pgSalesCounter.getSales().clear();
    for (PgSale sale : updatedSales) {
      sale.setSalesCounter(pgSalesCounter);
      pgSalesCounter.getSales().add(sale);
    }

    this.session.merge(pgSalesCounter);
  }

}
