package com.cinema.domain.entities.products;

import java.util.UUID;

public class Inventory {
  private UUID ID;
  private ProductInfo product;
  private int quantity;

  public Inventory(UUID ID, ProductInfo product, int quantity) {
    this.ID = ID;
    this.product = product;
    this.quantity = quantity;
  }

  public Inventory(ProductInfo product, int quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public ProductInfo getProduct() {
    return this.product;
  }

  public void setProduct(ProductInfo product) {
    this.product = product;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
