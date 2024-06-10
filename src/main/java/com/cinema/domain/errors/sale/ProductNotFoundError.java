package com.cinema.domain.errors.sale;

public class ProductNotFoundError extends Exception {
  public ProductNotFoundError() {
    super("Produto não encontrado!");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
