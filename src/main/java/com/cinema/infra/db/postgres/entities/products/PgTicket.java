package com.cinema.infra.db.postgres.entities.products;

import java.util.UUID;

import com.cinema.infra.db.postgres.entities.sale.PgCart;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "ticket")
public class PgTicket {
  @Id
  @GeneratedValue
  private UUID ID;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "ticket_infos_id", nullable = false)
  private PgTicketInfos ticketInfos;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "cart_id", nullable = true, insertable = true, updatable = true)
  private PgCart cart;

  public PgTicket() {
  }

  public PgTicket(UUID ID, PgTicketInfos ticketInfos, PgCart cart) {
    this.ID = ID;
    this.ticketInfos = ticketInfos;
    this.cart = cart;
  }

  public PgTicket(PgTicketInfos ticketInfos, PgCart cart) {
    this.ticketInfos = ticketInfos;
    this.cart = cart;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public PgTicketInfos getTicketInfos() {
    return this.ticketInfos;
  }

  public void setTicketInfos(PgTicketInfos ticketInfos) {
    this.ticketInfos = ticketInfos;
  }

  public PgCart getCart() {
    return this.cart;
  }

  public void setCart(PgCart cart) {
    this.cart = cart;
  }

}
