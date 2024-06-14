package com.cinema.infra.db.postgres.helpers.entities.sales;

import java.util.ArrayList;
import java.util.List;

import com.cinema.domain.entities.sale.Sale;
import com.cinema.domain.entities.sale.SalesCounter;
import com.cinema.infra.db.postgres.entities.sale.PgSale;
import com.cinema.infra.db.postgres.entities.sale.PgSalesCounter;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class SalesCounterConverter implements IEntityConverter<PgSalesCounter, SalesCounter> {

  @Override
  public SalesCounter convert(PgSalesCounter source) {
    List<Sale> sales = new ArrayList<>();

    if (source.getSales() == null) {
      source.setSales(new ArrayList<>());
    }

    source.getSales().stream().forEach(pgSale -> {
      Sale sale = new Sale(source.getID());

      sales.add(sale);
    });

    return new SalesCounter(source.getID(), source.getType(), source.getIsAvailable(), sales);
  }

  @Override
  public PgSalesCounter pgConverter(SalesCounter target) {
    List<PgSale> pgSales = new ArrayList<>();

    if (target.getSales() == null) {
      target.setSales(new ArrayList<>());
    }

    target.getSales().stream().forEach(sale -> {
      PgSale pgSale = new PgSale(target.getID());

      pgSales.add(pgSale);
    });

    return new PgSalesCounter(target.getID(), target.getType(), target.isAvailable(), pgSales);
  }

}
