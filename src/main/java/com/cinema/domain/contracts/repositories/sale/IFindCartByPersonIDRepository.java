package com.cinema.domain.contracts.repositories.sale;

import java.util.UUID;

import com.cinema.domain.entities.sale.Cart;

public interface IFindCartByPersonIDRepository {
  public Cart findCartByPersonID(UUID personID);
}
