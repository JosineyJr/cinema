package com.cinema.application.dtos.movies;

public class CreateGenreDTO {
  private String name;

  public CreateGenreDTO(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
