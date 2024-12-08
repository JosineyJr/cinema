package com.cinema.domain.contracts.repositories.sale;

import java.util.List;

import com.cinema.domain.entities.sale.TicketCart;

public interface IListTicketsCartRepository {
  public List<TicketCart> listTickets();
}
