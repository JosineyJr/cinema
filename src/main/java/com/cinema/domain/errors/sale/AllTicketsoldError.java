package com.cinema.domain.errors.sale;

public class AllTicketsoldError extends Exception {
  public AllTicketsoldError() {
    super("Todos os ingressos já foram vendidos.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
