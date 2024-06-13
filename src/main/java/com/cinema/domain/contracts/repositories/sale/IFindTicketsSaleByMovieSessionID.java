package com.cinema.domain.contracts.repositories.sale;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.entities.sale.TicketSale;

public interface IFindTicketsSaleByMovieSessionID {
  public List<TicketSale> findTicketsSaleByMovieSessionID(UUID movieSessionID);
}
