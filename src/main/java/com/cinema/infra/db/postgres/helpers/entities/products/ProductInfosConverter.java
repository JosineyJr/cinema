package com.cinema.infra.db.postgres.helpers.entities.products;

import com.cinema.domain.entities.products.ProductInfos;
import com.cinema.infra.db.postgres.entities.products.PgProductInfos;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class ProductInfosConverter implements IEntityConverter<PgProductInfos, ProductInfos> {

  @Override
  public ProductInfos convert(PgProductInfos source) {
    return new ProductInfos(source.getID(), source.getName(), source.getPrice());
  }

  @Override
  public PgProductInfos pgConverter(ProductInfos target) {
    return new PgProductInfos(target.getID(), target.getName(), target.getPrice());
  }

}
