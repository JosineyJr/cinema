package com.cinema.domain.usecases.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.IDeleteTicketRepository;
import com.cinema.domain.contracts.repositories.products.IFindTicketByIDRepository;
import com.cinema.domain.errors.sale.TicketNotFoundError;

public class RemoveTicketFromCartUseCase {
  private IDeleteTicketRepository deleteTicketRepository;
  private IFindTicketByIDRepository findTicketByIDRepository;

  public RemoveTicketFromCartUseCase(
      IDeleteTicketRepository deleteTicketRepository,
      IFindTicketByIDRepository findTicketByIDRepository) {
    this.deleteTicketRepository = deleteTicketRepository;
    this.findTicketByIDRepository = findTicketByIDRepository;
  }

  /**
   * Executes the use case to remove a ticket from the cart.
   *
   * @param ID the ID of the ticket to be removed
   * @throws TicketNotFoundError if the ticket with the given ID is not found
   */
  public void execute(UUID ID) throws TicketNotFoundError {
    if(this.findTicketByIDRepository.findTicketByID(ID) == null){
      throw new TicketNotFoundError();
    }

    this.deleteTicketRepository.deleteTicket(ID);
  }
}
