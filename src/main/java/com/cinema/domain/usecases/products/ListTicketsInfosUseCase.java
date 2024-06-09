package com.cinema.domain.usecases.products;

import java.util.List;

import com.cinema.domain.contracts.repositories.products.IListTicketsInfosRepository;
import com.cinema.domain.entities.products.TicketInfos;

public class ListTicketsInfosUseCase {
  private IListTicketsInfosRepository listTicketsInfosRepository;

  public ListTicketsInfosUseCase(IListTicketsInfosRepository listTicketsInfosRepository) {
    this.listTicketsInfosRepository = listTicketsInfosRepository;
  }

  /**
   * Executes the use case to list ticket information.
   *
   * @return a list of TicketInfos objects.
   */
  public List<TicketInfos> execute() {
    return this.listTicketsInfosRepository.listTickets();
  }
}
