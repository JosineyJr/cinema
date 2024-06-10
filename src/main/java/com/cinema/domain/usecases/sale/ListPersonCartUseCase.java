package com.cinema.domain.usecases.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.sale.IFindCartByPersonIDRepository;
import com.cinema.domain.entities.sale.Cart;
import com.cinema.domain.errors.sale.CartNotFoundError;

public class ListPersonCartUseCase {
  private IFindCartByPersonIDRepository findCartByPersonID;

  public ListPersonCartUseCase(IFindCartByPersonIDRepository findCartByPersonID) {
    this.findCartByPersonID = findCartByPersonID;
  }

  public Cart execute(UUID personID) throws CartNotFoundError {
    Cart cart = this.findCartByPersonID.findCartByPersonID(personID);

    if (cart == null) {
      throw new CartNotFoundError();
    }
    
    return cart;
  }
}
