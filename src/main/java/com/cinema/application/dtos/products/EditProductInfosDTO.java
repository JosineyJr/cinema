package com.cinema.application.dtos.products;

import java.util.UUID;

public class EditProductInfosDTO {
  private UUID ID;

  private String name;

  private double price;

  private int quantity;

  private UUID inventoryID;

  public EditProductInfosDTO(UUID ID, String name, double price, int quantity, UUID inventoryID) {
    this.ID = ID;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.inventoryID = inventoryID;
  }

  public UUID getID() {
    return this.ID;
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

  public UUID getInventoryID() {
    return this.inventoryID;
  }
}
