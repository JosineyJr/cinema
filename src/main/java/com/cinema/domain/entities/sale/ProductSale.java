package com.cinema.domain.entities.sale;

import java.util.UUID;

import com.cinema.domain.entities.products.Product;

public class ProductSale {
  private UUID ID;

  private Product product;

  private Sale sale;

  private double price;

  public ProductSale(UUID ID, Product product, Sale sale, double price) {
    this.ID = ID;
    this.product = product;
    this.sale = sale;
    this.price = price;
  }

  public ProductSale(Product product, Sale sale, double price) {
    this.product = product;
    this.sale = sale;
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

  public Sale getSale() {
    return this.sale;
  }

  public void setSale(Sale sale) {
    this.sale = sale;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
