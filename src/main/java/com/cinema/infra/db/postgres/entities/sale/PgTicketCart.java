package com.cinema.infra.db.postgres.entities.sale;

import java.util.UUID;

import com.cinema.infra.db.postgres.entities.products.PgTicket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "ticket_cart")
public class PgTicketCart {
  @Id
  @GeneratedValue
  private UUID ID;

  @ManyToOne
  @JoinColumn(nullable = false)
  private PgTicket ticket;

  @ManyToOne
  @JoinColumn(name = "cart_id", nullable = true)
  private PgCart cart;

  @Column
  private double price;

  public PgTicketCart() {
  }

  public PgTicketCart(UUID ID, PgTicket ticket, PgCart cart, double price) {
    this.ID = ID;
    this.ticket = ticket;
    this.cart = cart;
    this.price = price;
  }

  public PgTicketCart(PgTicket ticket, PgCart cart, double price) {
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

  public PgTicket getTicket() {
    return this.ticket;
  }

  public void setTicket(PgTicket ticket) {
    this.ticket = ticket;
  }

  public PgCart getCart() {
    return this.cart;
  }

  public void setCart(PgCart cart) {
    this.cart = cart;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
