package com.cinema.domain.errors.sale;

public class AllProductsSoldError extends Exception {
  public AllProductsSoldError() {
    super("Todos os produtos já foram vendidos.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
