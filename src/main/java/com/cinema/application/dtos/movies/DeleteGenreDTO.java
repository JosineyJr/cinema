package com.cinema.application.dtos.movies;

import java.util.UUID;

public class DeleteGenreDTO {
  private UUID ID;

  public DeleteGenreDTO(UUID ID) {
    this.ID = ID;
  }

  public UUID getID() {
    return ID;
  }
}
