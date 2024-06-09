package com.cinema.domain.entities.products;

import java.util.UUID;

import com.cinema.domain.entities.sale.Cart;

public class Product {
  private UUID ID;
  private ProductInfos productInfo;
  private Cart cart;

  public Product(UUID ID, ProductInfos productInfo, Cart cart) {
    this.ID = ID;
    this.productInfo = productInfo;
    this.cart = cart;
  }

  public Product(ProductInfos productInfo, Cart cart) {
    this.productInfo = productInfo;
    this.cart = cart;
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

  public void setProductInfo(ProductInfos productInfo) {
    this.productInfo = productInfo;
  }

  public Cart getCart() {
    return this.cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

}
