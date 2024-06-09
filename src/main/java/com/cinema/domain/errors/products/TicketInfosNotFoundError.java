package com.cinema.domain.errors.products;

public class TicketInfosNotFoundError extends Exception {
  public TicketInfosNotFoundError() {
    super("Informações do ingresso não encontradas.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
