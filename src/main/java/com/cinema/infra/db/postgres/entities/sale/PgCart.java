package com.cinema.infra.db.postgres.entities.sale;

import java.util.UUID;

import java.util.List;

import com.cinema.infra.db.postgres.entities.users.PgPerson;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "cart")
public class PgCart {
  @Id
  @GeneratedValue
  private UUID ID;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<PgTicketCart> tickets;

  @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<PgProductCart> products;

  @ManyToOne
  @JoinColumn(name = "person_id", nullable = true)
  private PgPerson person;

  public PgCart() {
  }

  public PgCart(UUID ID, List<PgTicketCart> tickets, List<PgProductCart> products, PgPerson person) {
    this.ID = ID;
    this.tickets = tickets;
    this.products = products;
    this.person = person;
  }

  public PgCart(List<PgTicketCart> tickets, List<PgProductCart> products, PgPerson person) {
    this.tickets = tickets;
    this.products = products;
    this.person = person;
  }

  public PgCart(UUID ID) {
    this.ID = ID;
  }

  public PgCart(PgPerson person) {
    this.person = person;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public List<PgTicketCart> getTickets() {
    return this.tickets;
  }

  public void setTickets(List<PgTicketCart> tickets) {
    this.tickets = tickets;
  }

  public List<PgProductCart> getProducts() {
    return this.products;
  }

  public void setProducts(List<PgProductCart> products) {
    this.products = products;
  }

  public PgPerson getPerson() {
    return this.person;
  }

  public void setPerson(PgPerson person) {
    this.person = person;
  }

}
