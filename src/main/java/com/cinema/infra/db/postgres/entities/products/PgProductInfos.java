package com.cinema.infra.db.postgres.entities.products;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "product_infos")
public class PgProductInfos {
  @Id
  @GeneratedValue
  private UUID ID;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private double price;

  public PgProductInfos() {
  }

  public PgProductInfos(String name, double price) {
    this.name = name;
    this.price = price;
  }

  public PgProductInfos(UUID ID, String name, double price) {
    this.ID = ID;
    this.name = name;
    this.price = price;
  }

  public UUID getID() {
    return this.ID;
  }

  public String getName() {
    return this.name;
  }

  public double getPrice() {
    return this.price;
  }
}
