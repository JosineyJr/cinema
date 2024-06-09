package com.cinema.domain.usecases.products;

import java.util.List;

import com.cinema.domain.contracts.repositories.products.IListTicketsRepository;
import com.cinema.domain.entities.products.Ticket;

public class ListTicketsUseCase {
  private IListTicketsRepository listTicketsRepository;

  public ListTicketsUseCase(IListTicketsRepository listTicketsRepository) {
    this.listTicketsRepository = listTicketsRepository;
  }

  public List<Ticket> execute() {
    return this.listTicketsRepository.listTickets();
  }
}
