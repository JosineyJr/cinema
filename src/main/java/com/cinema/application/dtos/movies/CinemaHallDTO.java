package com.cinema.application.dtos.movies;

import java.util.UUID;

/**
 * Represents a cinema hall data transfer object.
 */
public class CinemaHallDTO {
  private UUID ID;
  private String name;
  private int capacity;

  /**
   * Constructs a new CinemaHallDTO object with the specified ID, name, and capacity.
   * 
   * @param ID The ID of the cinema hall.
   * @param name The name of the cinema hall.
   * @param capacity The capacity of the cinema hall.
   */
  public CinemaHallDTO(UUID ID, String name, int capacity) {
    this.ID = ID;
    this.name = name;
    this.capacity = capacity;
  }

  /**
   * Returns the ID of the cinema hall.
   * 
   * @return The ID of the cinema hall.
   */
  public UUID getID() {
    return ID;
  }

  /**
   * Sets the ID of the cinema hall.
   * 
   * @param ID The ID of the cinema hall.
   */
  public void setID(UUID ID) {
    this.ID = ID;
  }

  /**
   * Returns the name of the cinema hall.
   * 
   * @return The name of the cinema hall.
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the cinema hall.
   * 
   * @param name The name of the cinema hall.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the capacity of the cinema hall.
   * 
   * @return The capacity of the cinema hall.
   */
  public int getCapacity() {
    return capacity;
  }

  /**
   * Sets the capacity of the cinema hall.
   * 
   * @param capacity The capacity of the cinema hall.
   */
  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
}
