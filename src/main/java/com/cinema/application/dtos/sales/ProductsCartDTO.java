package com.cinema.application.dtos.sales;

import java.util.UUID;

public class ProductsCartDTO {
  private UUID ID;
  private String name;
  private Double price;
  private UUID personID;

  public ProductsCartDTO(UUID ID, String name, Double price, UUID personID) {
    this.ID = ID;
    this.name = name;
    this.price = price;
    this.personID = personID;
  }

  public UUID getID() {
    return ID;
  }

  public String getName() {
    return name;
  }

  public Double getPrice() {
    return price;
  }

  public UUID getPersonID() {
    return personID;
  }
}
