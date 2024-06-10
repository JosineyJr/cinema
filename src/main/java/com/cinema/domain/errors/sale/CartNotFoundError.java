package com.cinema.domain.errors.sale;

public class CartNotFoundError extends Exception{
  public CartNotFoundError() {
    super("Carrinho não encontrado!");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
