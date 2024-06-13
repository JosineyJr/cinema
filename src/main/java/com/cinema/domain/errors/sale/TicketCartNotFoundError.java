package com.cinema.domain.errors.sale;

public class TicketCartNotFoundError extends Exception {
  public TicketCartNotFoundError() {
    super("Ticket não encontrado!");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
