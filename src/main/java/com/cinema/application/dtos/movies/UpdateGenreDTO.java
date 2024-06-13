package com.cinema.application.dtos.movies;

public class UpdateGenreDTO {
  private String ID;

  private String name;

  public UpdateGenreDTO(String ID, String name) {
    this.ID = ID;
    this.name = name;
  }

  public String getID() {
    return ID;
  }

  public String getName() {
    return name;
  }
}
