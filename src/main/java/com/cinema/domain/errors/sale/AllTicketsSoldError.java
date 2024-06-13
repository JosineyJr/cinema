package com.cinema.domain.errors.sale;

public class AllTicketsSoldError extends Exception {
  public AllTicketsSoldError() {
    super("Todos os ingressos já foram vendidos.");
  }

  public AllTicketsSoldError(int availableTickets, String startDate, String cinemaHallName) {
    super("Todos os ingressos já foram vendidos. Ingressos disponíveis: " + availableTickets + ". Data de início: "
        + startDate + ". Nome da sala: " + cinemaHallName);
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
