package com.cinema.domain.errors.sale;

public class AllProductsSoldError extends Exception {
  public AllProductsSoldError() {
    super("Todos os produtos já foram vendidos.");
  }

  public AllProductsSoldError(String productName) {
    super("Não há mais " + productName + " disponíveis.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
