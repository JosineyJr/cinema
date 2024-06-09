package com.cinema.domain.entities.products;

import java.util.UUID;

public class Inventory {
  private UUID ID;
  private ProductInfos productInfos;
  private int quantity;

  public Inventory(UUID ID, ProductInfos productInfos, int quantity) {
    this.ID = ID;
    this.productInfos = productInfos;
    this.quantity = quantity;
  }

  public Inventory(ProductInfos productInfos, int quantity) {
    this.productInfos = productInfos;
    this.quantity = quantity;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public ProductInfos getProductInfos() {
    return this.productInfos;
  }

  public void setProductInfos(ProductInfos product) {
    this.productInfos = product;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
