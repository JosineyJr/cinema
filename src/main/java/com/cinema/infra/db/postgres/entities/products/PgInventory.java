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
  @JoinColumn(name = "product_id", nullable = false)
  private PgProduct product;
}
