package com.cinema.application.dtos.movies;

/**
 * Represents a data transfer object for creating a movie.
 */
public class CreateMovieDTO {
  private String title;
  private String synopsis;
  private String director;
  private String genreID;
  private int duration;
  private int minimumAge;

  /**
   * Constructs a CreateMovieDTO object with the specified parameters.
   *
   * @param title       the title of the movie
   * @param synopsis    the synopsis of the movie
   * @param director    the director of the movie
   * @param genreID     the genre ID of the movie
   * @param duration    the duration of the movie in minutes
   * @param minimumAge  the minimum age requirement for the movie
   */
  public CreateMovieDTO(String title, String synopsis, String director, String genreID, int duration, int minimumAge) {
    this.title = title;
    this.synopsis = synopsis;
    this.director = director;
    this.genreID = genreID;
    this.duration = duration;
    this.minimumAge = minimumAge;
  }

  /**
   * Returns the title of the movie.
   *
   * @return the title of the movie
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Sets the title of the movie.
   *
   * @param title the title of the movie
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns the synopsis of the movie.
   *
   * @return the synopsis of the movie
   */
  public String getSynopsis() {
    return this.synopsis;
  }

  /**
   * Sets the synopsis of the movie.
   *
   * @param synopsis the synopsis of the movie
   */
  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  /**
   * Returns the director of the movie.
   *
   * @return the director of the movie
   */
  public String getDirector() {
    return this.director;
  }

  /**
   * Sets the director of the movie.
   *
   * @param director the director of the movie
   */
  public void setDirector(String director) {
    this.director = director;
  }

  /**
   * Returns the genre ID of the movie.
   *
   * @return the genre ID of the movie
   */
  public String getGenreID() {
    return this.genreID;
  }

  /**
   * Sets the genre ID of the movie.
   *
   * @param genreID the genre ID of the movie
   */
  public void setGenreID(String genreID) {
    this.genreID = genreID;
  }

  /**
   * Returns the duration of the movie in minutes.
   *
   * @return the duration of the movie in minutes
   */
  public int getDuration() {
    return this.duration;
  }

  /**
   * Sets the duration of the movie in minutes.
   *
   * @param duration the duration of the movie in minutes
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Returns the minimum age requirement for the movie.
   *
   * @return the minimum age requirement for the movie
   */
  public int getMinimumAge() {
    return this.minimumAge;
  }

  /**
   * Sets the minimum age requirement for the movie.
   *
   * @param minimumAge the minimum age requirement for the movie
   */
  public void setMinimumAge(int minimumAge) {
    this.minimumAge = minimumAge;
  }
}
