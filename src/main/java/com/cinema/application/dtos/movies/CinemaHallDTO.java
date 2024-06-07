package com.cinema.application.dtos.movies;

import java.util.UUID;

public class CinemaHallDTO {
  private UUID ID;
  private String name;
  private int capacity;

  public CinemaHallDTO(UUID ID, String name, int capacity) {
    this.ID = ID;
    this.name = name;
    this.capacity = capacity;
  }

  public UUID getID() {
    return ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
}
