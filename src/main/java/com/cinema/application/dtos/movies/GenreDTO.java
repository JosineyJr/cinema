package com.cinema.application.dtos.movies;

import java.util.UUID;

/**
 * Represents a Genre Data Transfer Object (DTO) used for transferring genre information.
 */
public class GenreDTO {
  private UUID ID;
  private String name;

  /**
   * Constructs a new GenreDTO object with the specified ID and name.
   *
   * @param ID   the ID of the genre
   * @param name the name of the genre
   */
  public GenreDTO(UUID ID, String name) {
    this.ID = ID;
    this.name = name;
  }

  /**
   * Returns the ID of the genre.
   *
   * @return the ID of the genre
   */
  public UUID getID() {
    return this.ID;
  }

  /**
   * Returns the name of the genre.
   *
   * @return the name of the genre
   */
  public String getName() {
    return name;
  }
}
