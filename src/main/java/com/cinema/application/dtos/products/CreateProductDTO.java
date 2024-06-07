package com.cinema.application.dtos.products;

public class CreateProductDTO {
  private String name;
  private double price;
  private int quantity;

  public CreateProductDTO(String name, double price, int quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  public String getName() {
    return this.name;
  }

  public double getPrice() {
    return this.price;
  }

  public int getQuantity() {
    return this.quantity;
  }
}
