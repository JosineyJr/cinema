package com.cinema.application.dtos.movies;

import java.util.UUID;

/**
 * This class represents a data transfer object (DTO) for deleting a cinema hall.
 */
public class DeleteCinemaHallDTO {
  private UUID ID;

  /**
   * Constructs an empty DeleteCinemaHallDTO object.
   */
  public DeleteCinemaHallDTO() {
  }

  /**
   * Constructs a DeleteCinemaHallDTO object with the specified ID.
   * 
   * @param ID the ID of the cinema hall to be deleted
   */
  public DeleteCinemaHallDTO(UUID ID) {
    this.ID = ID;
  }

  /**
   * Gets the ID of the cinema hall.
   * 
   * @return the ID of the cinema hall
   */
  public UUID getID() {
    return this.ID;
  }
}
