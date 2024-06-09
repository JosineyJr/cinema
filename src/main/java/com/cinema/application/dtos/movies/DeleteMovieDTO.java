package com.cinema.application.dtos.movies;

import java.util.UUID;

public class DeleteMovieDTO {
  private UUID ID;

  public DeleteMovieDTO(UUID ID) {
    this.ID = ID;
  }

  public UUID getID() {
    return ID;
  }
}
