package com.cinema.application.dtos.products;

import java.util.UUID;

public class EditProductDTO {
  private UUID ID;

  private String name;

  private double price;

  private int quantity;

  private UUID inventoryID;

  /**
   * Constructs a new EditProductDTO object with the specified parameters.
   *
   * @param ID          The ID of the product.
   * @param name        The name of the product.
   * @param price       The price of the product.
   * @param quantity    The quantity of the product.
   * @param inventoryID The ID of the inventory associated with the product.
   */
  public EditProductDTO(UUID ID, String name, double price, int quantity, UUID inventoryID) {
    this.ID = ID;
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
   * Returns the name of the product.
   *
   * @return The name of the product.
   */
  public String getName() {
    return this.name;
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
   * Returns the quantity of the product.
   *
   * @return The quantity of the product.
   */
  public int getQuantity() {
    return this.quantity;
  }

  /**
   * Returns the ID of the inventory associated with the product.
   *
   * @return The ID of the inventory associated with the product.
   */
  public UUID getInventoryID() {
    return this.inventoryID;
  }
}
