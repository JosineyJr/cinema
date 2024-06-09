package com.cinema.domain.usecases.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.IFindTicketInfosByIDRepository;
import com.cinema.domain.contracts.repositories.sale.ICreateCartRepository;
import com.cinema.domain.contracts.repositories.sale.IFindCartByPersonIDRepository;
import com.cinema.domain.contracts.repositories.sale.IUpdateCartRepository;
import com.cinema.domain.contracts.repositories.users.IFindPersonByIDRepository;
import com.cinema.domain.entities.products.Ticket;
import com.cinema.domain.entities.products.TicketInfos;
import com.cinema.domain.entities.sale.Cart;
import com.cinema.domain.entities.users.Person;
import com.cinema.domain.errors.products.TicketInfosNotFoundError;
import com.cinema.domain.errors.sale.AllTicketsSoldError;

public class AddTicketToCartUseCase {
  IFindTicketInfosByIDRepository findTicketInfosByID;
  IFindCartByPersonIDRepository findCartByPersonID;
  IFindPersonByIDRepository findPersonByIDRepository;
  ICreateCartRepository createCartRepository;
  IUpdateCartRepository updateCartRepository;

  public AddTicketToCartUseCase(
      IFindTicketInfosByIDRepository findTicketInfosByID,
      IFindCartByPersonIDRepository findCartByPersonID,
      IFindPersonByIDRepository findPersonByIDRepository,
      ICreateCartRepository createCartRepository,
      IUpdateCartRepository updateCartRepository) {
    this.findTicketInfosByID = findTicketInfosByID;
    this.findCartByPersonID = findCartByPersonID;
    this.findPersonByIDRepository = findPersonByIDRepository;
    this.createCartRepository = createCartRepository;
    this.updateCartRepository = updateCartRepository;
  }

  public void execute(UUID ticketInfoID, UUID personID) throws TicketInfosNotFoundError, AllTicketsSoldError {
    TicketInfos ticketInfos = this.findTicketInfosByID.findTicketInfosByID(ticketInfoID);

    if (ticketInfos == null) {
      throw new TicketInfosNotFoundError();
    }

    Cart cart = this.findCartByPersonID.findCartByPersonID(personID);

    if (cart == null) {
      Person person = this.findPersonByIDRepository.findPersonByID(personID);
      cart = new Cart(person);

      cart = this.createCartRepository.createCart(cart);
    }

    Ticket ticket = new Ticket(ticketInfos, cart);

    boolean isAdd = cart.addTicket(ticket);

    if (isAdd == false) {
      throw new AllTicketsSoldError();
    }

    this.updateCartRepository.updateCart(cart);
  }
}
