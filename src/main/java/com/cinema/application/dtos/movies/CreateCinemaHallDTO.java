package com.cinema.application.dtos.movies;

public class CreateCinemaHallDTO {
  private int numberOfChairs;
  private String name;

  public CreateCinemaHallDTO(int numberOfChairs, String name) {
    this.numberOfChairs = numberOfChairs;
    this.name = name;
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
