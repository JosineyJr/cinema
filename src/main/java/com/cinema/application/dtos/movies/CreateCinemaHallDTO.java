package com.cinema.application.dtos.movies;

/**
 * Represents a data transfer object for creating a cinema hall.
 */
public class CreateCinemaHallDTO {
  private int capacity;
  private String name;

  /**
   * Constructs a new CreateCinemaHallDTO object with the specified capacity and name.
   *
   * @param capacity the capacity of the cinema hall
   * @param name the name of the cinema hall
   */
  public CreateCinemaHallDTO(int capacity, String name) {
    this.capacity = capacity;
    this.name = name;
  }

  /**
   * Gets the capacity of the cinema hall.
   *
   * @return the capacity of the cinema hall
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * Sets the capacity of the cinema hall.
   *
   * @param capacity the capacity of the cinema hall
   */
  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  /**
   * Gets the name of the cinema hall.
   *
   * @return the name of the cinema hall
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name of the cinema hall.
   *
   * @param name the name of the cinema hall
   */
  public void setName(String name) {
    this.name = name;
  }
}
