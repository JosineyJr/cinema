package com.cinema.domain.entities.sale;

import java.util.UUID;

import com.cinema.domain.entities.products.Product;

public class ProductCart {
  private UUID ID;
  private Product product;
  private Cart cart;
  private double price;

  public ProductCart(UUID ID, Product product, Cart cart, double price) {
    this.ID = ID;
    this.product = product;
    this.cart = cart;
    this.price = price;
  }

  public ProductCart(Product product, Cart cart, double price) {
    this.product = product;
    this.cart = cart;
    this.price = price;
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

  public Cart getCart() {
    return this.cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
