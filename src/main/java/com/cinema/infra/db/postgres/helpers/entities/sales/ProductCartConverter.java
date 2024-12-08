package com.cinema.infra.db.postgres.helpers.entities.sales;

import com.cinema.domain.entities.sale.Cart;
import com.cinema.domain.entities.sale.ProductCart;
import com.cinema.infra.db.postgres.entities.sale.PgCart;
import com.cinema.infra.db.postgres.entities.sale.PgProductCart;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;
import com.cinema.infra.db.postgres.helpers.entities.products.ProductConverter;

public class ProductCartConverter implements IEntityConverter<PgProductCart, ProductCart> {
  private ProductConverter productConverter = new ProductConverter();

  @Override
  public ProductCart convert(PgProductCart source) {
    return new ProductCart(source.getID(), productConverter.convert(source.getProduct()),
        new Cart(source.getCart().getID()), source.getPrice());
  }

  @Override
  public PgProductCart pgConverter(ProductCart target) {
    return new PgProductCart(target.getID(), productConverter.pgConverter(target.getProduct()),
        new PgCart(target.getCart().getID()), target.getPrice());
  }

}
