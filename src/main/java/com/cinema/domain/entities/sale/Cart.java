package com.cinema.domain.entities.sale;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.entities.users.Person;

public class Cart {
  private UUID ID;
  private List<TicketCart> tickets;
  private List<ProductCart> products;
  private Person person;

  public Cart(UUID ID, List<TicketCart> tickets, List<ProductCart> products, Person person) {
    this.ID = ID;
    this.tickets = tickets;
    this.products = products;
    this.person = person;
  }

  public Cart(List<TicketCart> tickets, List<ProductCart> products, Person person) {
    this.tickets = tickets;
    this.products = products;
    this.person = person;
  }

  public Cart(UUID ID) {
    this.ID = ID;
  }

  public Cart(Person person) {
    this.person = person;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public List<TicketCart> getTickets() {
    return this.tickets;
  }

  public void setTickets(List<TicketCart> tickets) {
    this.tickets = tickets;
  }

  public List<ProductCart> getProducts() {
    return this.products;
  }

  public void setProducts(List<ProductCart> products) {
    this.products = products;
  }

  public Person getPerson() {
    return this.person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public boolean addTicket(TicketCart ticket) {
    int count = 0;
    for (TicketCart t : tickets) {
      if (t.getTicket().getMovieSession().getID().equals(ticket.getTicket().getMovieSession().getID())) {
        count++;
      }
    }

    if (count < ticket.getTicket().getMovieSession().getCinemaHall().getCapacity()) {
      this.tickets.add(ticket);

      return true;
    }

    return false;
  }

  public boolean addProduct(ProductCart product, int quantity) {
    int count = 0;
    for (ProductCart p : products) {
      if (p.getProduct().getID().equals(product.getProduct().getID())) {
        count++;
      }
    }

    if (count < quantity) {
      this.products.add(product);

      return true;
    }

    return false;
  }

  public boolean removeTicket(TicketCart ticket) {
    return this.tickets.remove(ticket);
  }
}
