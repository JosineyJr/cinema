package com.cinema.main.views.helpers;

import java.util.UUID;

public class Item {
  private final UUID ID;
  private final String value;

  public Item(UUID ID, String value) {
    this.ID = ID;
    this.value = value;
  }

  public UUID getID() {
    return ID;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return value;
  }
}
