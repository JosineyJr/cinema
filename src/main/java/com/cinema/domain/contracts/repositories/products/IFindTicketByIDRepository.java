package com.cinema.domain.contracts.repositories.products;

import java.util.UUID;

import com.cinema.domain.entities.products.Ticket;

public interface IFindTicketByIDRepository {
  public Ticket findByID(UUID ticketID);
}
