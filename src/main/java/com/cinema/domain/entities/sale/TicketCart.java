package com.cinema.domain.entities.sale;

import java.util.UUID;

import com.cinema.domain.entities.products.Ticket;

public class TicketCart {
  private UUID ID;
  private Ticket ticket;
  private Cart cart;
  private double price;

  public TicketCart(UUID ID, Ticket ticket, Cart cart, double price) {
    this.ID = ID;
    this.ticket = ticket;
    this.cart = cart;
    this.price = price;
  }

  public TicketCart(Ticket ticket, Cart cart, double price) {
    this.ticket = ticket;
    this.cart = cart;
    this.price = price;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public Ticket getTicket() {
    return this.ticket;
  }

  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
  }

  public Cart getCart() {
    return this.cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
