package com.cinema.domain.errors.products;

public class ProductInfosNotFoundError extends Exception {
  public ProductInfosNotFoundError() {
    super("Produto não encontrado");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
