package com.cinema.application.dtos.movies;

import java.util.UUID;

/**
 * A data transfer object (DTO) class representing the request to delete a movie.
 */
public class DeleteMovieDTO {
  private UUID ID;

  /**
   * Constructs a new DeleteMovieDTO object with the specified ID.
   *
   * @param ID the ID of the movie to be deleted
   */
  public DeleteMovieDTO(UUID ID) {
    this.ID = ID;
  }

  /**
   * Gets the ID of the movie to be deleted.
   *
   * @return the ID of the movie
   */
  public UUID getID() {
    return ID;
  }
}
