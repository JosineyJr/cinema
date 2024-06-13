package com.cinema.infra.db.postgres.helpers.entities.products;

import com.cinema.domain.entities.products.Product;
import com.cinema.infra.db.postgres.entities.products.PgProduct;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class ProductConverter implements IEntityConverter<PgProduct, Product> {

  @Override
  public Product convert(PgProduct source) {
    return new Product(source.getID(), source.getName(), source.getPrice());
  }

  @Override
  public PgProduct pgConverter(Product target) {
    return new PgProduct(target.getID(), target.getName(), target.getPrice());
  }

}
