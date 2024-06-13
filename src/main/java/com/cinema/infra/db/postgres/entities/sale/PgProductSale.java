package com.cinema.infra.db.postgres.entities.sale;

import java.util.UUID;

import com.cinema.infra.db.postgres.entities.products.PgProduct;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "product_sale")
public class PgProductSale {
  @Id
  @GeneratedValue
  private UUID ID;

  @ManyToOne
  @JoinColumn(nullable = false)
  private PgProduct product;

  @ManyToOne
  @JoinColumn(name = "sale_id", nullable = true)
  private PgSale sale;

  @Column
  private double price;

  public PgProductSale() {
  }

  public PgProductSale(UUID ID, PgProduct product, PgSale sale, double price) {
    this.ID = ID;
    this.product = product;
    this.sale = sale;
    this.price = price;
  }

  public PgProductSale(PgProduct product, PgSale sale, double price) {
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

  public PgProduct getProduct() {
    return this.product;
  }

  public void setProduct(PgProduct product) {
    this.product = product;
  }

  public PgSale getSale() {
    return this.sale;
  }

  public void setSale(PgSale sale) {
    this.sale = sale;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

}
