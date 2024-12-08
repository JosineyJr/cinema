package com.cinema.infra.db.postgres.entities.products;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity(name = "inventory")
public class PgInventory {
  @Id
  @GeneratedValue
  private UUID ID;

  @Column(nullable = false)
  private int quantity;

  @OneToOne
  @JoinColumn(nullable = false)
  private PgProduct product;

  public PgInventory() {
  }

  public PgInventory(int quantity, PgProduct product) {
    this.quantity = quantity;
    this.product = product;
  }

  public PgInventory(UUID ID, int quantity, PgProduct product) {
    this.ID = ID;
    this.quantity = quantity;
    this.product = product;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public PgProduct getProduct() {
    return this.product;
  }

  public void setProduct(PgProduct product) {
    this.product = product;
  }

}
