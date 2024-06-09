package com.cinema.infra.db.postgres.helpers.entities.products;

import com.cinema.domain.entities.products.Product;
import com.cinema.domain.entities.sale.Cart;
import com.cinema.infra.db.postgres.entities.products.PgProduct;
import com.cinema.infra.db.postgres.entities.sale.PgCart;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class ProductConverter implements IEntityConverter<PgProduct, Product> {
  private ProductInfosConverter productInfosConverter = new ProductInfosConverter();

  @Override
  public Product convert(PgProduct source) {
    return new Product(source.getID(), productInfosConverter.convert(source.getProductInfos()),
        new Cart(source.getCart().getID()));
  }

  @Override
  public PgProduct pgConverter(Product target) {
    return new PgProduct(target.getID(), productInfosConverter.pgConverter(target.getProductInfos()),
        new PgCart(target.getCart().getID()));
  }

}
