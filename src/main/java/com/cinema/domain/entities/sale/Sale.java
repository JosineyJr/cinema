package com.cinema.domain.entities.sale;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;

import com.cinema.domain.entities.users.Person;

public class Sale {
  private UUID ID;

  private List<TicketSale> ticketSales;

  private List<ProductSale> productSales;

  private double totalPrice;

  private LocalDateTime saleDate;

  private Person person;

  private SalesCounter salesCounter;

  public Sale(UUID ID, List<TicketSale> ticketSales, List<ProductSale> productSales, double totalPrice,
      LocalDateTime saleDate, Person person, SalesCounter salesCounter) {
    this.ID = ID;
    this.ticketSales = ticketSales;
    this.productSales = productSales;
    this.totalPrice = totalPrice;
    this.saleDate = saleDate;
    this.person = person;
    this.salesCounter = salesCounter;
  }

  public Sale(List<TicketSale> ticketSales, List<ProductSale> productSales, double totalPrice, LocalDateTime saleDate,
      Person person, SalesCounter salesCounter) {
    this.ticketSales = ticketSales;
    this.productSales = productSales;
    this.totalPrice = totalPrice;
    this.saleDate = saleDate;
    this.person = person;
    this.salesCounter = salesCounter;
  }

  public Sale(UUID ID) {
    this.ID = ID;
  }

  public Sale(Person person, SalesCounter salesCounter) {
    this.person = person;
    this.salesCounter = salesCounter;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public List<TicketSale> getTicketSales() {
    return this.ticketSales;
  }

  public void setTicketSales(List<TicketSale> ticketSales) {
    this.ticketSales = ticketSales;
  }

  public List<ProductSale> getProductSales() {
    return this.productSales;
  }

  public void setProductSales(List<ProductSale> productSales) {
    this.productSales = productSales;
  }

  public double getTotalPrice() {
    return this.totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Person getPerson() {
    return this.person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }

  public SalesCounter getSalesCounter() {
    return this.salesCounter;
  }

  public void setSalesCounter(SalesCounter salesCounter) {
    this.salesCounter = salesCounter;
  }

  public LocalDateTime getSaleDate() {
    return this.saleDate;
  }

  public void setSaleDate(LocalDateTime saleDate) {
    this.saleDate = saleDate;
  }

  public void calculateTotalPrice() {
    double totalPrice = 0;

    for (TicketSale ticketSale : this.ticketSales) {
      totalPrice += ticketSale.getPrice();
    }

    for (ProductSale productSale : this.productSales) {
      totalPrice += productSale.getPrice();
    }

    this.totalPrice = totalPrice;
  }
}
