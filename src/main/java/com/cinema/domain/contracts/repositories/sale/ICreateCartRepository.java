package com.cinema.domain.contracts.repositories.sale;

import com.cinema.domain.entities.sale.Cart;

public interface ICreateCartRepository {
  public Cart createCart(Cart cart);
}
