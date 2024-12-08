package com.cinema.domain.usecases.products;

import java.util.List;

import com.cinema.domain.contracts.repositories.sale.IListTicketsCartRepository;
import com.cinema.domain.entities.sale.TicketCart;

public class ListTicketsCartUseCase {
  private IListTicketsCartRepository listTicketsRepository;

  public ListTicketsCartUseCase(IListTicketsCartRepository listTicketsRepository) {
    this.listTicketsRepository = listTicketsRepository;
  }

  /**
   * Executes the use case to list all tickets.
   *
   * @return a list of tickets
   */
  public List<TicketCart> execute() {
    return this.listTicketsRepository.listTickets();
  }
}
