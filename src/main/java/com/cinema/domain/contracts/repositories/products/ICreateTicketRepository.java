package com.cinema.domain.contracts.repositories.products;

import com.cinema.domain.entities.products.Ticket;

public interface ICreateTicketRepository {
  public void createTicket(Ticket ticket);
}
