package com.cinema.application.dtos.products;

import java.util.UUID;

import com.cinema.domain.entities.products.Product;

public class InventoryDTO {
  private UUID ID;
  private Product product;
  private int quantity;

  public InventoryDTO(UUID ID, Product product, int quantity) {
    this.ID = ID;
    this.product = product;
    this.quantity = quantity;
  }

  public InventoryDTO(Product product, int quantity) {
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
