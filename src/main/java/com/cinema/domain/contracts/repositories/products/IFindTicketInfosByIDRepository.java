package com.cinema.domain.contracts.repositories.products;

import java.util.UUID;

import com.cinema.domain.entities.products.TicketInfos;

public interface IFindTicketInfosByIDRepository {
  public TicketInfos findTicketInfosByID(UUID ticketInfoID);
}
