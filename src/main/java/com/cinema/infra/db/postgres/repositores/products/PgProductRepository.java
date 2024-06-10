package com.cinema.infra.db.postgres.repositores.products;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.IDeleteProductRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByIDRepository;
import com.cinema.domain.entities.products.Product;
import com.cinema.infra.db.postgres.entities.products.PgProduct;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgProductRepository
    extends PgRepository
    implements IDeleteProductRepository,
    IFindProductByIDRepository {

  public void deleteProduct(UUID id) {
    PgProduct pgProduct = this.session.get(PgProduct.class, id);

    this.session.remove(pgProduct);
  }

  public Product findProductByID(UUID ID) {
    PgProduct pgProduct = this.session.get(PgProduct.class, ID);

    return ConvertEntities.convertProduct(pgProduct);
  }
}
