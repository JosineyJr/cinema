package com.cinema.main.views.movies;

import com.cinema.application.dtos.movies.MovieDTO;

/**
 * The MovieModel class represents a singleton model for storing movie data.
 */
public class MovieModel {
  private static MovieModel movieModel = null;
  private MovieDTO movie;

  private MovieModel() {
  }

  /**
   * Returns the instance of the MovieModel class.
   *
   * @return The instance of the MovieModel class.
   */
  public static MovieModel getInstance() {
    if (movieModel == null) {
      movieModel = new MovieModel();
    }

    return movieModel;
  }

  /**
   * Returns the movie object stored in the model.
   *
   * @return The movie object stored in the model.
   */
  public MovieDTO getMovie() {
    return movie;
  }

  /**
   * Sets the movie object in the model.
   *
   * @param movie The movie object to be set in the model.
   */
  public void setMovie(MovieDTO movie) {
    this.movie = movie;
  }
}
