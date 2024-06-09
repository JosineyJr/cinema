package com.cinema.infra.db.postgres.entities.sale;

import java.util.UUID;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity(name = "sale")
public class PgSale {
  @Id
  @GeneratedValue
  private UUID ID;

  @ManyToOne
  private PgCart cart;

  @Column(name = "total_price", nullable = false)
  private double totalPrice;

  @Column(name = "sale_date", nullable = false)
  private LocalDateTime saleDate;

  public PgSale() {
  }

  public PgSale(UUID ID, PgCart cart, double totalPrice, LocalDateTime saleDate) {
    this.ID = ID;
    this.cart = cart;
    this.totalPrice = totalPrice;
    this.saleDate = saleDate;
  }

  public PgSale(PgCart cart, double totalPrice, LocalDateTime saleDate) {
    this.cart = cart;
    this.totalPrice = totalPrice;
    this.saleDate = saleDate;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public PgCart getCart() {
    return this.cart;
  }

  public void setCart(PgCart cart) {
    this.cart = cart;
  }

  public double getTotalPrice() {
    return this.totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public LocalDateTime getSaleDate() {
    return this.saleDate;
  }

  public void setSaleDate(LocalDateTime saleDate) {
    this.saleDate = saleDate;
  }
}
