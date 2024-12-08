package com.cinema.domain.usecases.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.sale.IDeleteTicketCartRepository;
import com.cinema.domain.contracts.repositories.sale.IFindTicketCartByIDRepository;
import com.cinema.domain.errors.sale.TicketCartNotFoundError;

public class RemoveTicketCartFromCartUseCase {
  private IDeleteTicketCartRepository deleteTicketRepository;
  private IFindTicketCartByIDRepository findTicketCartByIDRepository;

  public RemoveTicketCartFromCartUseCase(
      IDeleteTicketCartRepository deleteTicketRepository,
      IFindTicketCartByIDRepository findTicketCartByIDRepository) {
    this.deleteTicketRepository = deleteTicketRepository;
    this.findTicketCartByIDRepository = findTicketCartByIDRepository;
  }

  /**
   * Executes the use case to remove a ticket from the cart.
   *
   * @param ID the ID of the ticket to be removed
   * @throws TicketCartNotFoundError if the ticket with the given ID is not found
   */
  public void execute(UUID ID) throws TicketCartNotFoundError {
    if (this.findTicketCartByIDRepository.findByID(ID) == null) {
      throw new TicketCartNotFoundError();
    }

    this.deleteTicketRepository.deleteTicketCart(ID);
  }
}
