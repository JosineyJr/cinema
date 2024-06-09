package com.cinema.domain.contracts.repositories.products;

import com.cinema.domain.entities.products.TicketInfos;

public interface ICreateTicketInfosRepository {
  public void createTicket(TicketInfos ticket);
}
