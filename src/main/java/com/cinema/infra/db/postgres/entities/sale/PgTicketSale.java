package com.cinema.infra.db.postgres.entities.sale;

import java.util.UUID;

import com.cinema.infra.db.postgres.entities.products.PgTicket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "ticket_sale")
public class PgTicketSale {
  @Id
  @GeneratedValue
  private UUID ID;

  @ManyToOne
  @JoinColumn(nullable = false)
  private PgTicket ticket;

  @ManyToOne
  @JoinColumn(name = "sale_id", nullable = true)
  private PgSale sale;

  @Column
  private double price;

  public PgTicketSale() {
  }

  public PgTicketSale(UUID ID, PgTicket ticket, PgSale sale, double price) {
    this.ID = ID;
    this.ticket = ticket;
    this.sale = sale;
    this.price = price;
  }

  public PgTicketSale(PgTicket ticket, PgSale sale, double price) {
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

  public PgTicket getTicket() {
    return this.ticket;
  }

  public void setTicket(PgTicket ticket) {
    this.ticket = ticket;
  }

  public PgSale getSale() {
    return this.sale;
  }

  public void setSale(PgSale sale) {
    this.sale = sale;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
