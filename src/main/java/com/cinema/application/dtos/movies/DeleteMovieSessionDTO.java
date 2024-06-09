package com.cinema.application.dtos.movies;

import java.util.UUID;

public class DeleteMovieSessionDTO {
  private UUID ID;

  public DeleteMovieSessionDTO(UUID ID) {
    this.ID = ID;
  }

  public UUID getID() {
    return this.ID;
  }
}
