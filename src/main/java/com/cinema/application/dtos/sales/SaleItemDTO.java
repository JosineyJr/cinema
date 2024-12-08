package com.cinema.application.dtos.sales;

import java.util.UUID;

public class SaleItemDTO {
  private UUID ID;
  private String name;
  private double price;

  public SaleItemDTO(UUID ID, String name, double price) {
    this.ID = ID;
    this.name = name;
    this.price = price;
  }

  public UUID getID() {
    return ID;
  }

  public String getName() {
    return name;
  }

  public double getPrice() {
    return price;
  }
}
