package com.cinema.domain.entities.products;

import java.util.UUID;

public class Inventory {
  private UUID ID;
  private ProductInfos productInfo;
  private int quantity;

  public Inventory(UUID ID, ProductInfos productInfo, int quantity) {
    this.ID = ID;
    this.productInfo = productInfo;
    this.quantity = quantity;
  }

  public Inventory(ProductInfos productInfo, int quantity) {
    this.productInfo = productInfo;
    this.quantity = quantity;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public ProductInfos getProductInfos() {
    return this.productInfo;
  }

  public void setProductInfos(ProductInfos product) {
    this.productInfo = product;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
