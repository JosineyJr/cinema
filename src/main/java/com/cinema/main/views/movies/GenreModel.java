package com.cinema.main.views.movies;

import com.cinema.application.dtos.movies.GenreDTO;

/**
 * The GenreModel class represents a model for a genre in a movie.
 * It provides methods to get and set the genre information.
 */
public class GenreModel {
  private static GenreModel genreModel = null;
  private GenreDTO genre;

  private GenreModel() {
  }

  /**
   * Returns the instance of the GenreModel class.
   * If the instance does not exist, it creates a new one.
   *
   * @return The instance of the GenreModel class.
   */
  public static GenreModel getInstance() {
    if (genreModel == null) {
      genreModel = new GenreModel();
    }

    return genreModel;
  }

  /**
   * Returns the genre information.
   *
   * @return The genre information.
   */
  public GenreDTO getGenre() {
    return genre;
  }

  /**
   * Sets the genre information.
   *
   * @param genre The genre information to be set.
   */
  public void setGenre(GenreDTO genre) {
    this.genre = genre;
  }
}
