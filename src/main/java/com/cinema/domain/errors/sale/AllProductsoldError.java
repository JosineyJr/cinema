package com.cinema.domain.errors.sale;

public class AllProductsoldError extends Exception {
  public AllProductsoldError() {
    super("Todos os produtos já foram vendidos.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
