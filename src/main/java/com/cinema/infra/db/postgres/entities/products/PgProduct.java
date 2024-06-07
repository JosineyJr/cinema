package com.cinema.infra.db.postgres.entities.products;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "product")
public class PgProduct {
  @Id
  @GeneratedValue
  private UUID ID;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private double price;
}
