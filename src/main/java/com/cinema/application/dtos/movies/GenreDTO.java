package com.cinema.application.dtos.movies;

import java.util.UUID;

public class GenreDTO {
  private UUID ID;
  private String name;

  public GenreDTO(UUID ID, String name) {
    this.ID = ID;
    this.name = name;
  }

  public UUID getID() {
    return this.ID;
  }

  public String getName() {
    return name;
  }
}
