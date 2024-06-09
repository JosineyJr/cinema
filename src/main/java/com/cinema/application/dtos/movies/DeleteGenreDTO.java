package com.cinema.application.dtos.movies;

import java.util.UUID;

/**
 * A data transfer object (DTO) class representing the request to delete a genre.
 */
public class DeleteGenreDTO {
  private UUID ID;

  /**
   * Constructs a new DeleteGenreDTO object with the specified ID.
   *
   * @param ID the ID of the genre to be deleted
   */
  public DeleteGenreDTO(UUID ID) {
    this.ID = ID;
  }

  /**
   * Gets the ID of the genre to be deleted.
   *
   * @return the ID of the genre
   */
  public UUID getID() {
    return ID;
  }
}
