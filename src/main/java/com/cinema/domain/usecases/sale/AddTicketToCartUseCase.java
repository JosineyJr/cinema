package com.cinema.domain.usecases.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.IFindTicketByIDRepository;
import com.cinema.domain.contracts.repositories.sale.ICreateCartRepository;
import com.cinema.domain.contracts.repositories.sale.IFindCartByPersonIDRepository;
import com.cinema.domain.contracts.repositories.sale.IUpdateCartRepository;
import com.cinema.domain.contracts.repositories.users.IFindPersonByIDRepository;
import com.cinema.domain.entities.products.Ticket;
import com.cinema.domain.entities.sale.Cart;
import com.cinema.domain.entities.sale.TicketCart;
import com.cinema.domain.entities.users.Person;
import com.cinema.domain.errors.products.TicketNotFoundError;
import com.cinema.domain.errors.sale.AllTicketsSoldError;
import com.cinema.domain.errors.users.PersonNotFoundError;

public class AddTicketToCartUseCase {
  IFindTicketByIDRepository findTicketByID;
  IFindCartByPersonIDRepository findCartByPersonID;
  IFindPersonByIDRepository findPersonByIDRepository;
  ICreateCartRepository createCartRepository;
  IUpdateCartRepository updateCartRepository;

  public AddTicketToCartUseCase(
      IFindTicketByIDRepository findTicketByID,
      IFindCartByPersonIDRepository findCartByPersonID,
      IFindPersonByIDRepository findPersonByIDRepository,
      ICreateCartRepository createCartRepository,
      IUpdateCartRepository updateCartRepository) {
    this.findTicketByID = findTicketByID;
    this.findCartByPersonID = findCartByPersonID;
    this.findPersonByIDRepository = findPersonByIDRepository;
    this.createCartRepository = createCartRepository;
    this.updateCartRepository = updateCartRepository;
  }

  public void execute(UUID ticketID, UUID personID)
      throws TicketNotFoundError, AllTicketsSoldError, PersonNotFoundError {
    Ticket ticket = this.findTicketByID.findByID(ticketID);

    if (ticket == null) {
      throw new TicketNotFoundError();
    }

    Cart cart = this.findCartByPersonID.findCartByPersonID(personID);

    if (cart == null) {
      Person person = this.findPersonByIDRepository.findPersonByID(personID);

      if (person == null) {
        throw new PersonNotFoundError();
      }

      cart = new Cart(person);

      cart = this.createCartRepository.createCart(cart);
    }

    TicketCart ticketCart = new TicketCart(ticket, cart, ticket.getPrice());

    boolean isAdd = cart.addTicket(ticketCart);

    if (isAdd == false) {
      throw new AllTicketsSoldError();
    }

    this.updateCartRepository.updateCart(cart);
  }
}
