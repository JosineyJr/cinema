package com.cinema.domain.usecases.products;

import java.util.List;

import com.cinema.domain.contracts.repositories.products.IListTicketsInfosRepository;
import com.cinema.domain.entities.products.TicketInfos;

public class ListTicketsInfosUseCase {
  private IListTicketsInfosRepository listTicketsInfosRepository;

  public ListTicketsInfosUseCase(IListTicketsInfosRepository listTicketsInfosRepository) {
    this.listTicketsInfosRepository = listTicketsInfosRepository;
  }

  public List<TicketInfos> execute() {
    return this.listTicketsInfosRepository.listTickets();
  }
}
