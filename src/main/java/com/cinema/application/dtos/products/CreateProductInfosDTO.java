package com.cinema.application.dtos.products;

/**
 * Represents a data transfer object for creating product information.
 */
public class CreateProductInfosDTO {
  private String name;
  private double price;
  private int quantity;

  /**
   * Constructs a new CreateProductInfosDTO with the specified name, price, and quantity.
   *
   * @param name     the name of the product
   * @param price    the price of the product
   * @param quantity the quantity of the product
   */
  public CreateProductInfosDTO(String name, double price, int quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  /**
   * Gets the name of the product.
   *
   * @return the name of the product
   */
  public String getName() {
    return this.name;
  }

  /**
   * Gets the price of the product.
   *
   * @return the price of the product
   */
  public double getPrice() {
    return this.price;
  }

  /**
   * Gets the quantity of the product.
   *
   * @return the quantity of the product
   */
  public int getQuantity() {
    return this.quantity;
  }
}
