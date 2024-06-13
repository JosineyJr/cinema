package com.cinema.domain.usecases.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.sale.IDeleteTicketCartRepository;
import com.cinema.domain.contracts.repositories.sale.IFindTicketCartByIDRepository;
import com.cinema.domain.errors.sale.TicketNotFoundError;

public class RemoveTicketFromCartUseCase {
  private IDeleteTicketCartRepository deleteTicketRepository;
  private IFindTicketCartByIDRepository findTicketByIDRepository;

  public RemoveTicketFromCartUseCase(
      IDeleteTicketCartRepository deleteTicketRepository,
      IFindTicketCartByIDRepository findTicketByIDRepository) {
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
    if (this.findTicketByIDRepository.findByID(ID) == null) {
      throw new TicketNotFoundError();
    }

    this.deleteTicketRepository.deleteTicketCart(ID);
  }
}
