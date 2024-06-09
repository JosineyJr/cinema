package com.cinema.domain.errors.sale;

public class AllTicketsSoldError extends Exception {
  public AllTicketsSoldError() {
    super("Todos os ingressos já foram vendidos.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
