package com.cinema.infra.db.postgres.repositores.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.IDeleteProductRepository;
import com.cinema.domain.contracts.repositories.sale.IFindProductCartByIDRepository;
import com.cinema.domain.entities.sale.ProductCart;
import com.cinema.infra.db.postgres.entities.sale.PgCart;
import com.cinema.infra.db.postgres.entities.sale.PgProductCart;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgProductCartRepository
    extends PgRepository
    implements IDeleteProductRepository,
    IFindProductCartByIDRepository {

  public void deleteProduct(UUID id) {
    PgProductCart pgProduct = this.session.get(PgProductCart.class, id);

    PgCart cart = pgProduct.getCart();
    if (cart != null) {
      cart.getProducts().remove(pgProduct);
      pgProduct.setCart(null);
    }

    this.session.remove(pgProduct);
  }

  public ProductCart findProductByID(UUID ID) {
    PgProductCart pgProduct = this.session.get(PgProductCart.class, ID);

    return ConvertEntities.convertProductCart(pgProduct);
  }
}
