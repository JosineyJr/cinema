package com.cinema.application.dtos.movies;

import java.util.UUID;

public class DeleteCinemaHallDTO {
  private UUID ID;

  public DeleteCinemaHallDTO() {
  }

  public DeleteCinemaHallDTO(UUID ID) {
    this.ID = ID;
  }

  public UUID getID() {
    return this.ID;
  }
}
