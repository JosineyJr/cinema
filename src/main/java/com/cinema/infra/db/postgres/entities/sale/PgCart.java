package com.cinema.infra.db.postgres.entities.sale;

import java.util.UUID;

import java.util.List;

import com.cinema.infra.db.postgres.entities.products.PgProduct;
import com.cinema.infra.db.postgres.entities.products.PgTicket;
import com.cinema.infra.db.postgres.entities.users.PgPerson;

import jakarta.persistence.Entity;
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

  @OneToMany(mappedBy = "cart")
  private List<PgTicket> tickets;

  @OneToMany(mappedBy = "cart")
  private List<PgProduct> products;

  @ManyToOne
  @JoinColumn(name = "person_id", nullable = true)
  private PgPerson person;

  public PgCart() {
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public List<PgTicket> getTickets() {
    return this.tickets;
  }

  public void setTickets(List<PgTicket> tickets) {
    this.tickets = tickets;
  }

  public List<PgProduct> getProducts() {
    return this.products;
  }

  public void setProducts(List<PgProduct> products) {
    this.products = products;
  }

  public PgPerson getPerson() {
    return this.person;
  }

  public void setPerson(PgPerson person) {
    this.person = person;
  }

}
