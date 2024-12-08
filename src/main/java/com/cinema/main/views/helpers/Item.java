package com.cinema.main.views.helpers;

import java.util.UUID;

/**
 * Represents an item with a unique ID and a value.
 */
public class Item {
  private final UUID ID;
  private final String value;

  /**
   * Constructs a new Item object with the specified ID and value.
   *
   * @param ID    the unique ID of the item
   * @param value the value of the item
   */
  public Item(UUID ID, String value) {
    this.ID = ID;
    this.value = value;
  }

  /**
   * Returns the ID of the item.
   *
   * @return the ID of the item
   */
  public UUID getID() {
    return ID;
  }

  /**
   * Returns the value of the item.
   *
   * @return the value of the item
   */
  public String getValue() {
    return value;
  }

  /**
   * Returns a string representation of the item.
   *
   * @return a string representation of the item
   */
  @Override
  public String toString() {
    return value;
  }
}
