package com.cinema.infra.db.postgres.repositores.sale;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.contracts.repositories.sale.ICreateSaleRepository;
import com.cinema.domain.contracts.repositories.sale.IListSalesRepository;
import com.cinema.domain.contracts.repositories.sale.IUpdateSaleRepository;
import com.cinema.domain.entities.sale.Sale;
import com.cinema.infra.db.postgres.entities.sale.PgSale;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgSaleRepository
    extends PgRepository
    implements ICreateSaleRepository,
    IUpdateSaleRepository,
    IListSalesRepository {

  @Override
  public void updateSale(Sale sale) {
    PgSale pgSale = ConvertEntities.pgConvertSale(sale);

    this.session.merge(pgSale);
  }

  @Override
  public UUID createSale(Sale sale) {
    PgSale pgSale = ConvertEntities.pgConvertSale(sale);

    this.session.persist(pgSale);

    return pgSale.getID();
  }

  public List<Sale> listSales() {
    List<PgSale> pgSales = this.session.createQuery("from sale", PgSale.class).list();

    return pgSales.stream().map(ConvertEntities::convertSale).toList();
  }
}
