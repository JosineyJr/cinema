package com.cinema.domain.entities.movies;

import java.util.UUID;

public class Genre {
  private UUID ID;
  private String name;


  public Genre(UUID ID, String name) {
    this.ID = ID;
    this.name = name;
  }

  public Genre(String name) {
    this.name = name;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
