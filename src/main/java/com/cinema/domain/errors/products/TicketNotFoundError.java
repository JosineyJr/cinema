package com.cinema.domain.errors.products;

public class TicketNotFoundError extends Exception {
  public TicketNotFoundError() {
    super("rmações do ingresso não encontradas.");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
