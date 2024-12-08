package com.cinema.domain.errors.sale;

public class SalesCounterNotFoundError extends Exception {
  public SalesCounterNotFoundError() {
    super("Balcão de vendas não encontrado.");
  }

  @Override
  public String toString() {
    return getMessage();
  }
}
