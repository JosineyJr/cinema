package com.cinema.application.dtos.movies;

import java.util.UUID;

import com.cinema.domain.entities.movies.Genre;

/**
 * Represents a movie data transfer object (DTO).
 */
public class MovieDTO {
  private UUID ID;
  private String title;
  private String synopsis;
  private String director;
  private Genre genre;
  private int duration;
  private int minimumAge;

  /**
   * Constructs a new MovieDTO object with the specified ID, title, synopsis, director, genre, duration, and minimum age.
   * 
   * @param ID The ID of the movie.
   * @param title The title of the movie.
   * @param synopsis The synopsis of the movie.
   * @param director The director of the movie.
   * @param genre The genre of the movie.
   * @param duration The duration of the movie in minutes.
   * @param minimumAge The minimum age required to watch the movie.
   */
  public MovieDTO(UUID ID, String title, String synopsis, String director, Genre genre, int duration, int minimumAge) {
    this.ID = ID;
    this.title = title;
    this.synopsis = synopsis;
    this.director = director;
    this.genre = genre;
    this.duration = duration;
    this.minimumAge = minimumAge;
  }

  /**
   * Constructs a new MovieDTO object with the specified title, synopsis, director, genre, duration, and minimum age.
   * 
   * @param title The title of the movie.
   * @param synopsis The synopsis of the movie.
   * @param director The director of the movie.
   * @param genre The genre of the movie.
   * @param duration The duration of the movie in minutes.
   * @param minimumAge The minimum age required to watch the movie.
   */
  public MovieDTO(String title, String synopsis, String director, Genre genre, int duration, int minimumAge) {
    this.title = title;
    this.synopsis = synopsis;
    this.director = director;
    this.genre = genre;
    this.duration = duration;
    this.minimumAge = minimumAge;
  }

  /**
   * Returns the ID of the movie.
   * 
   * @return The ID of the movie.
   */
  public UUID getID() {
    return this.ID;
  }

  /**
   * Sets the ID of the movie.
   * 
   * @param ID The ID of the movie.
   */
  public void setID(UUID ID) {
    this.ID = ID;
  }

  /**
   * Returns the title of the movie.
   * 
   * @return The title of the movie.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * Sets the title of the movie.
   * 
   * @param title The title of the movie.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Returns the synopsis of the movie.
   * 
   * @return The synopsis of the movie.
   */
  public String getSynopsis() {
    return this.synopsis;
  }

  /**
   * Sets the synopsis of the movie.
   * 
   * @param synopsis The synopsis of the movie.
   */
  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  /**
   * Returns the director of the movie.
   * 
   * @return The director of the movie.
   */
  public String getDirector() {
    return this.director;
  }

  /**
   * Sets the director of the movie.
   * 
   * @param director The director of the movie.
   */
  public void setDirector(String director) {
    this.director = director;
  }

  /**
   * Returns the genre of the movie.
   * 
   * @return The genre of the movie.
   */
  public String getGenre() {
    return this.genre.getName();
  }

  /**
   * Returns the ID of the genre associated with this movie.
   *
   * @return the ID of the genre
   */
  public UUID getGenreID() {
    return this.genre.getID();
  }

  /**
   * Sets the genre of the movie.
   * 
   * @param genre The genre of the movie.
   */
  public void setGenre(Genre genre) {
    this.genre = genre;
  }

  /**
   * Returns the duration of the movie in minutes.
   * 
   * @return The duration of the movie in minutes.
   */
  public int getDuration() {
    return this.duration;
  }

  /**
   * Sets the duration of the movie in minutes.
   * 
   * @param duration The duration of the movie in minutes.
   */
  public void setDuration(int duration) {
    this.duration = duration;
  }

  /**
   * Returns the minimum age required to watch the movie.
   * 
   * @return The minimum age required to watch the movie.
   */
  public int getMinimumAge() {
    return this.minimumAge;
  }

  /**
   * Sets the minimum age required to watch the movie.
   * 
   * @param minimumAge The minimum age required to watch the movie.
   */
  public void setMinimumAge(int minimumAge) {
    this.minimumAge = minimumAge;
  }

}
