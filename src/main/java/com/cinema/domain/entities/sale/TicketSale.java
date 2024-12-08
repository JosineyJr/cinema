package com.cinema.domain.entities.sale;

import java.util.UUID;

import com.cinema.domain.entities.products.Ticket;

public class TicketSale {
  private UUID ID;

  private Ticket ticket;

  private Sale sale;

  private double price;

  public TicketSale(UUID ID, Ticket ticket, Sale sale, double price) {
    this.ID = ID;
    this.ticket = ticket;
    this.sale = sale;
    this.price = price;
  }

  public TicketSale(Ticket ticket, Sale sale, double price) {
    this.ticket = ticket;
    this.sale = sale;
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

  public Sale getSale() {
    return this.sale;
  }

  public void setSale(Sale sale) {
    this.sale = sale;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
