package com.cinema.domain.errors.sale;

public class TicketNotFoundError extends Exception {
  public TicketNotFoundError() {
    super("Ticket não encontrado!");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
