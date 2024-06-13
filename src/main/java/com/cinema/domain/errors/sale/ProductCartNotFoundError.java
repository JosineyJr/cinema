package com.cinema.domain.errors.sale;

public class ProductCartNotFoundError extends Exception {
  public ProductCartNotFoundError() {
    super("Produto não encontrado!");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
