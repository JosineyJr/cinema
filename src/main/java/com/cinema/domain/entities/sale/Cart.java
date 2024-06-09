package com.cinema.domain.entities.sale;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.entities.products.Ticket;
import com.cinema.domain.entities.users.Person;
import com.cinema.domain.entities.products.ProductInfo;

public class Cart {
  private UUID ID;
  private List<Ticket> tickets;
  private List<ProductInfo> products;
  private Person person;

  public Cart(UUID ID, List<Ticket> tickets, List<ProductInfo> products, Person person) {
    this.ID = ID;
    this.tickets = tickets;
    this.products = products;
    this.person = person;
  }

  public Cart(List<Ticket> tickets, List<ProductInfo> products, Person person) {
    this.tickets = tickets;
    this.products = products;
    this.person = person;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public List<Ticket> getTickets() {
    return this.tickets;
  }

  public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
  }

  public List<ProductInfo> getProducts() {
    return this.products;
  }

  public void setProducts(List<ProductInfo> products) {
    this.products = products;
  }

  public Person getPerson() {
    return this.person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

}
