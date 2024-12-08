package com.cinema.domain.contracts.repositories.sale;

import java.util.UUID;

import com.cinema.domain.entities.sale.TicketCart;

public interface IFindTicketCartByIDRepository {
  public TicketCart findByID(UUID ID);
}
