package com.cinema.domain.contracts.repositories.sale;

import com.cinema.domain.entities.sale.Cart;

public interface IUpdateCartRepository {
  public void updateCart(Cart cart);
}
