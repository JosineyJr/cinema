package com.cinema.domain.contracts.repositories.products;

import java.util.List;

import com.cinema.domain.entities.products.TicketInfos;

public interface IListTicketsInfosRepository {
  public List<TicketInfos> listTickets();
}
