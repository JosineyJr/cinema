package com.cinema.domain.entities.sale;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.entities.products.Ticket;
import com.cinema.domain.entities.users.Person;
import com.cinema.domain.entities.products.Product;

public class Cart {
  private UUID ID;
  private List<Ticket> tickets;
  private List<Product> products;
  private Person person;

  public Cart(UUID ID, List<Ticket> tickets, List<Product> products, Person person) {
    this.ID = ID;
    this.tickets = tickets;
    this.products = products;
    this.person = person;
  }

  public Cart(List<Ticket> tickets, List<Product> products, Person person) {
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

  public List<Ticket> getTickets() {
    return this.tickets;
  }

  public void setTickets(List<Ticket> tickets) {
    this.tickets = tickets;
  }

  public List<Product> getProducts() {
    return this.products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }

  public Person getPerson() {
    return this.person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public boolean addTicket(Ticket ticket) {
    int count = 0;
    for (Ticket t : tickets) {
      if (t.getTicketInfos().getMovieSession().getID().equals(ticket.getTicketInfos().getMovieSession().getID())) {
        count++;
      }
    }

    if (count < ticket.getTicketInfos().getMovieSession().getCinemaHall().getCapacity()) {
      this.tickets.add(ticket);

      return true;
    }

    return false;
  }

  public boolean addProduct(Product product, int quantity) {
    int count = 0;
    for (Product p : products) {
      if (p.getProductInfos().getID().equals(product.getProductInfos().getID())) {
        count++;
      }
    }

    if (count < quantity) {
      this.products.add(product);

      return true;
    }

    return false;
  }

  public boolean removeTicket(Ticket ticket) {
    return this.tickets.remove(ticket);
  }
}
