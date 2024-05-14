package com.cinema.application.dtos.movies;

public class CreateCinemaHallDTO {
  private int capacity;
  private String name;

  public CreateCinemaHallDTO(int capacity, String name) {
    this.capacity = capacity;
    this.name = name;
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
