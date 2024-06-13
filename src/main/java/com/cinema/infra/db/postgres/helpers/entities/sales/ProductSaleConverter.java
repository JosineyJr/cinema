package com.cinema.infra.db.postgres.helpers.entities.sales;

import com.cinema.domain.entities.sale.ProductSale;
import com.cinema.domain.entities.sale.Sale;
import com.cinema.infra.db.postgres.entities.sale.PgProductSale;
import com.cinema.infra.db.postgres.entities.sale.PgSale;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;
import com.cinema.infra.db.postgres.helpers.entities.products.ProductConverter;

public class ProductSaleConverter implements IEntityConverter<PgProductSale, ProductSale> {
  private ProductConverter productConverter = new ProductConverter();

  @Override
  public ProductSale convert(PgProductSale source) {
    return new ProductSale(source.getID(), productConverter.convert(source.getProduct()),
        new Sale(source.getSale().getID()), source.getPrice());
  }

  @Override
  public PgProductSale pgConverter(ProductSale target) {
    return new PgProductSale(target.getID(), productConverter.pgConverter(target.getProduct()),
        new PgSale(target.getSale().getID()), target.getPrice());
  }

}
