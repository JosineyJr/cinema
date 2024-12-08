package com.cinema.domain.entities.movies;

import java.util.UUID;

public class CinemaHall {
  private UUID ID;
  private int capacity;
  private String name;

  public CinemaHall(UUID ID, int capacity, String name) {
    this.ID = ID;
    this.capacity = capacity;
    this.name = name;
  }

  public CinemaHall(int capacity, String name) {
    this.capacity = capacity;
    this.name = name;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public int getCapacity() {
    return this.capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
