package com.cinema.application.dtos.movies;

import java.util.UUID;

/**
 * A data transfer object (DTO) class representing the request to delete a movie session.
 */
public class DeleteMovieSessionDTO {
  private UUID ID;

  /**
   * Constructs a new DeleteMovieSessionDTO with the specified ID.
   *
   * @param ID the ID of the movie session to be deleted
   */
  public DeleteMovieSessionDTO(UUID ID) {
    this.ID = ID;
  }

  /**
   * Gets the ID of the movie session to be deleted.
   *
   * @return the ID of the movie session
   */
  public UUID getID() {
    return this.ID;
  }
}
