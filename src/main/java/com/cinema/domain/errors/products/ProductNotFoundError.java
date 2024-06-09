package com.cinema.domain.errors.products;

public class ProductNotFoundError extends Exception {
  public ProductNotFoundError() {
    super("Produto não encontrado");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
