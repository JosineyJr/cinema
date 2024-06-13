package com.cinema.domain.entities.products;

import java.util.UUID;

public class Inventory {
  private UUID ID;
  private Product product;
  private int quantity;

  public Inventory(UUID ID, Product product, int quantity) {
    this.ID = ID;
    this.product = product;
    this.quantity = quantity;
  }

  public Inventory(Product product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public Product getProduct() {
    return this.product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
