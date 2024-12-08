package com.cinema.domain.errors.products;

public class ProductAlreadyExistsError extends Exception{
  public ProductAlreadyExistsError() {
    super("Produto já cadastrado.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
