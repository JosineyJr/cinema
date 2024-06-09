package com.cinema.domain.contracts.repositories.products;

import java.util.List;

import com.cinema.domain.entities.products.Ticket;

public interface IListTicketsRepository {
  public List<Ticket> listTickets();
}
