package com.cinema.infra.db.postgres.entities.sale;

import java.util.UUID;

import com.cinema.infra.db.postgres.entities.users.PgPerson;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "sale")
public class PgSale {
  @Id
  @GeneratedValue
  private UUID ID;

  @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<PgProductSale> products;

  @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<PgTicketSale> tickets;

  @Column(name = "total_price", nullable = true)
  private double totalPrice;

  @Column(name = "sale_date", nullable = true)
  private LocalDateTime saleDate;

  @ManyToOne
  @JoinColumn(name = "sales_counter_id", nullable = true)
  private PgSalesCounter salesCounter;

  @ManyToOne
  private PgPerson person;

  public PgSale() {
  }

  public PgSale(UUID ID, List<PgProductSale> products, List<PgTicketSale> tickets, double totalPrice,
      LocalDateTime saleDate,
      PgSalesCounter salesCounter, PgPerson person) {
    this.ID = ID;
    this.products = products;
    this.tickets = tickets;
    this.totalPrice = totalPrice;
    this.saleDate = saleDate;
    this.salesCounter = salesCounter;
    this.person = person;
  }

  public PgSale(List<PgProductSale> products, List<PgTicketSale> tickets, double totalPrice, LocalDateTime saleDate,
      PgSalesCounter salesCounter, PgPerson person) {
    this.products = products;
    this.tickets = tickets;
    this.totalPrice = totalPrice;
    this.saleDate = saleDate;
    this.salesCounter = salesCounter;
    this.person = person;
  }

  public PgSale(UUID ID) {
    this.ID = ID;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public List<PgProductSale> getProducts() {
    return this.products;
  }

  public void setProducts(List<PgProductSale> products) {
    this.products = products;
  }

  public List<PgTicketSale> getTickets() {
    return this.tickets;
  }

  public void setTickets(List<PgTicketSale> tickets) {
    this.tickets = tickets;
  }

  public double getTotalPrice() {
    return this.totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public LocalDateTime getSaleDate() {
    return this.saleDate;
  }

  public void setSaleDate(LocalDateTime saleDate) {
    this.saleDate = saleDate;
  }

  public PgSalesCounter getSalesCounter() {
    return this.salesCounter;
  }

  public void setSalesCounter(PgSalesCounter salesCounter) {
    this.salesCounter = salesCounter;
  }

  public PgPerson getPerson() {
    return this.person;
  }

  public void setPerson(PgPerson person) {
    this.person = person;
  }

}
