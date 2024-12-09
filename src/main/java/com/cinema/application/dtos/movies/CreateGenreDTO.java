package com.cinema.application.dtos.movies;

/**
 * A data transfer object (DTO) class representing the information needed to create a new genre.
 */
public class CreateGenreDTO {
  private String name;

  /**
   * Constructs a new CreateGenreDTO object with the specified name.
   *
   * @param name the name of the genre
   */
  public CreateGenreDTO(String name) {
    this.name = name;
  }

  public CreateGenreDTO() {
    // construtor sem argumentos
  }

  /**
   * Gets the name of the genre.
   *
   * @return the name of the genre
   */
  public String getName() {
    return this.name;
  }

  /**
   * Sets the name of the genre.
   *
   * @param name the name of the genre
   */
  public void setName(String name) {
    this.name = name;
  }
}
