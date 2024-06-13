package com.cinema.domain.entities.products;

import java.util.UUID;

public class Product {
  private UUID ID;
  private String name;
  private double price;

  public Product(UUID ID, String name, double price) {
    this.ID = ID;
    this.name = name;
    this.price = price;
  }

  public Product(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
