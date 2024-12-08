package com.cinema.infra.db.postgres.entities.sale;

import java.util.UUID;

import com.cinema.infra.db.postgres.entities.products.PgProduct;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "product_cart")
public class PgProductCart {
  @Id
  @GeneratedValue
  private UUID ID;

  @ManyToOne
  @JoinColumn(nullable = false)
  private PgProduct product;

  @ManyToOne
  @JoinColumn(nullable = true)
  private PgCart cart;

  @Column
  private double price;

  public PgProductCart() {
  }

  public PgProductCart(UUID ID, PgProduct product, PgCart cart, double price) {
    this.ID = ID;
    this.product = product;
    this.cart = cart;
    this.price = price;
  }

  public PgProductCart(PgProduct product, PgCart cart, double price) {
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

  public PgProduct getProduct() {
    return this.product;
  }

  public void setProduct(PgProduct product) {
    this.product = product;
  }

  public PgCart getCart() {
    return this.cart;
  }

  public void setCart(PgCart cart) {
    this.cart = cart;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
