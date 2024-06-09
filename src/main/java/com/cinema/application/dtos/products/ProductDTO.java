package com.cinema.application.dtos.products;

import java.util.UUID;

public class ProductDTO {
  private UUID ID;
  private String name;
  private double price;
  private int quantity;

  public ProductDTO(UUID ID, String name, double price, int quantity) {
    this.ID = ID;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public ProductDTO(String name, double price, int quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
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

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
}
