package com.cinema.application.dtos.products;

import java.util.UUID;

public class ProductDTO {
  private UUID ID;
  private String name;
  private double price;

  public ProductDTO(UUID ID, String name, double price) {
    this.ID = ID;
    this.name = name;
    this.price = price;
  }

  public ProductDTO(String name, double price) {
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
