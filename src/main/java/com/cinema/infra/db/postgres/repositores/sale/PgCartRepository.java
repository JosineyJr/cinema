package com.cinema.infra.db.postgres.repositores.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.sale.ICleanCartByPersonIDRepository;
import com.cinema.domain.contracts.repositories.sale.ICreateCartRepository;
import com.cinema.domain.contracts.repositories.sale.IFindCartByPersonIDRepository;
import com.cinema.domain.contracts.repositories.sale.IUpdateCartRepository;
import com.cinema.domain.entities.sale.Cart;
import com.cinema.infra.db.postgres.entities.sale.PgCart;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgCartRepository extends PgRepository
    implements IFindCartByPersonIDRepository, ICreateCartRepository, IUpdateCartRepository,
    ICleanCartByPersonIDRepository {

  @Override
  public void updateCart(Cart cart) {
    PgCart pgCart = ConvertEntities.pgConvertCart(cart);
    this.session.merge(pgCart);
  }

  @Override
  public Cart createCart(Cart cart) {
    PgCart pgCart = ConvertEntities.pgConvertCart(cart);

    this.session.persist(pgCart);

    cart.setID(pgCart.getID());

    return cart;
  }

  @Override
  public Cart findCartByPersonID(UUID personID) {
    PgCart pgCart = this.session.createQuery("from cart where person.id = :personID", PgCart.class)
        .setParameter("personID", personID)
        .getSingleResultOrNull();

    if (pgCart == null) {
      return null;
    }

    return ConvertEntities.convertCart(pgCart);
  }

  @Override
  public void cleanCart(UUID personID) {
    PgCart pgCart = this.session.createQuery("from cart where person.id = :personID", PgCart.class)
        .setParameter("personID", personID)
        .getSingleResultOrNull();

    this.session.remove(pgCart);
  }

}
