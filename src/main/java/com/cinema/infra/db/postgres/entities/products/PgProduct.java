package com.cinema.infra.db.postgres.entities.products;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * Represents product information in the database.
 */
@Entity(name = "product")
public class PgProduct {
  @Id
  @GeneratedValue
  private UUID ID;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private double price;

  /**
   * Default constructor.
   */
  public PgProduct() {
  }

  /**
   * Constructor with name and price parameters.
   * 
   * @param name  the name of the product
   * @param price the price of the product
   */
  public PgProduct(String name, double price) {
    this.name = name;
    this.price = price;
  }

  /**
   * Constructor with ID, name, and price parameters.
   * 
   * @param ID    the ID of the product
   * @param name  the name of the product
   * @param price the price of the product
   */
  public PgProduct(UUID ID, String name, double price) {
    this.ID = ID;
    this.name = name;
    this.price = price;
  }

  /**
   * Get the ID of the product.
   * 
   * @return the ID of the product
   */
  public UUID getID() {
    return this.ID;
  }

  /**
   * Get the name of the product.
   * 
   * @return the name of the product
   */
  public String getName() {
    return this.name;
  }

  /**
   * Get the price of the product.
   * 
   * @return the price of the product
   */
  public double getPrice() {
    return this.price;
  }

  /**
   * Set the name of the product.
   * 
   * @param name the name of the product
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Set the price of the product.
   * 
   * @param price the price of the product
   */
  public void setPrice(double price) {
    this.price = price;
  }
}
