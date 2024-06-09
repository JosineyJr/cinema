package com.cinema.domain.entities.products;

import java.util.UUID;

import com.cinema.domain.entities.sale.Cart;

public class Ticket {
  private UUID ID;
  private TicketInfos ticketInfos;
  private Cart cart;

  public Ticket(UUID ID, TicketInfos ticketInfos, Cart cart) {
    this.ID = ID;
    this.ticketInfos = ticketInfos;
    this.cart = cart;
  }

  public Ticket(TicketInfos ticketInfos, Cart cart) {
    this.ticketInfos = ticketInfos;
    this.cart = cart;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public TicketInfos getTicketInfos() {
    return this.ticketInfos;
  }

  public void setTicketInfos(TicketInfos ticketInfos) {
    this.ticketInfos = ticketInfos;
  }

  public Cart getCart() {
    return this.cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

}
