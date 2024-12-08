package com.cinema.domain.contracts.repositories.sale;

import java.util.UUID;

import com.cinema.domain.entities.sale.TicketSale;

public interface ICreateTicketSaleRepository {
  public UUID create(TicketSale ticketSale);
}
