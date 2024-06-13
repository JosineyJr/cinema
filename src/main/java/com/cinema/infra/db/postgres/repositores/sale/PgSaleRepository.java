package com.cinema.infra.db.postgres.repositores.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.sale.ICreateSaleRepository;
import com.cinema.domain.contracts.repositories.sale.IUpdateSaleRepository;
import com.cinema.domain.entities.sale.Sale;
import com.cinema.infra.db.postgres.entities.sale.PgSale;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgSaleRepository extends PgRepository implements ICreateSaleRepository, IUpdateSaleRepository {

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
}
