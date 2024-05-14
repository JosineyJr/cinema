package com.cinema.domain.entities.movies;

import java.util.UUID;

public class CinemaHall {
  private UUID ID;
  private int numberOfChairs;
  private String name;

  public CinemaHall(UUID ID, int numberOfChairs, String name) {
    this.ID = ID;
    this.numberOfChairs = numberOfChairs;
    this.name = name;
  }

  public CinemaHall(int numberOfChairs, String name) {
    this.numberOfChairs = numberOfChairs;
    this.name = name;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public int getNumberOfChairs() {
    return this.numberOfChairs;
  }

  public void setNumberOfChairs(int numberOfChairs) {
    this.numberOfChairs = numberOfChairs;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
