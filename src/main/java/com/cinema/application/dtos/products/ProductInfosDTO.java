package com.cinema.application.dtos.products;

import java.util.UUID;

/**
 * The ProductInfosDTO class represents the data transfer object for product
 * information.
 * It contains the ID, name, price, quantity, and inventory ID of a product.
 */
public class ProductInfosDTO {
  private UUID ID;
  private String name;
  private double price;
  private int quantity;
  private UUID inventoryID;

  /**
   * Constructs a new ProductInfosDTO object with the specified ID, name, price,
   * quantity, and inventory ID.
   * 
   * @param ID          The ID of the product.
   * @param name        The name of the product.
   * @param price       The price of the product.
   * @param quantity    The quantity of the product.
   * @param inventoryID The ID of the inventory associated with the product.
   */
  public ProductInfosDTO(UUID ID, String name, double price, int quantity, UUID inventoryID) {
    this.ID = ID;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.inventoryID = inventoryID;
  }

  /**
   * Constructs a new ProductInfosDTO object with the specified name, price,
   * quantity, and inventory ID.
   * 
   * @param name        The name of the product.
   * @param price       The price of the product.
   * @param quantity    The quantity of the product.
   * @param inventoryID The ID of the inventory associated with the product.
   */
  public ProductInfosDTO(String name, double price, int quantity, UUID inventoryID) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
    this.inventoryID = inventoryID;
  }

  /**
   * Returns the ID of the product.
   * 
   * @return The ID of the product.
   */
  public UUID getID() {
    return this.ID;
  }

  /**
   * Sets the ID of the product.
   * 
   * @param ID The ID of the product.
   */
  public void setID(UUID ID) {
    this.ID = ID;
  }

  /**
   * Returns the name of the product.
   * 
   * @return The name of the product.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name of the product.
   * 
   * @param name The name of the product.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the price of the product.
   * 
   * @return The price of the product.
   */
  public double getPrice() {
    return this.price;
  }

  /**
   * Sets the price of the product.
   * 
   * @param price The price of the product.
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Returns the quantity of the product.
   * 
   * @return The quantity of the product.
   */
  public int getQuantity() {
    return this.quantity;
  }

  /**
   * Sets the quantity of the product.
   * 
   * @param quantity The quantity of the product.
   */
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  /**
   * Returns the ID of the inventory associated with the product.
   * 
   * @return The ID of the inventory associated with the product.
   */
  public UUID getInventoryID() {
    return this.inventoryID;
  }

  /**
   * Sets the ID of the inventory associated with the product.
   * 
   * @param inventoryID The ID of the inventory associated with the product.
   */
  public void setInventoryID(UUID inventoryID) {
    this.inventoryID = inventoryID;
  }
}
