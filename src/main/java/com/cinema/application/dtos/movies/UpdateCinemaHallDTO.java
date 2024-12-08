package com.cinema.application.dtos.movies;

import java.util.UUID;

public class UpdateCinemaHallDTO {
  private UUID ID;
  private String name;
  private int capacity;

  public UpdateCinemaHallDTO(UUID ID, String name, int capacity) {
    this.ID = ID;
    this.name = name;
    this.capacity = capacity;
  }

  public UUID getID() {
    return ID;
  }

  public String getName() {
    return name;
  }

  public int getCapacity() {
    return capacity;
  }
}
