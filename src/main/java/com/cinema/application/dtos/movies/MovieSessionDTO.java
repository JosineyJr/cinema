package com.cinema.application.dtos.movies;

import java.util.UUID;

/**
 * Represents a movie session data transfer object.
 */
public class MovieSessionDTO {
  private UUID ID;
  private String movie;
  private String cinemaHall;
  private String startTime;

  /**
   * Constructs a new MovieSessionDTO object with the specified ID, movie, cinema hall, and start time.
   * 
   * @param ID The ID of the movie session.
   * @param movie The name of the movie.
   * @param cinemaHall The name of the cinema hall.
   * @param startTime The start time of the movie session.
   */
  public MovieSessionDTO(UUID ID, String movie, String cinemaHall, String startTime) {
    this.ID = ID;
    this.movie = movie;
    this.cinemaHall = cinemaHall;
    this.startTime = startTime;
  }

  /**
   * Returns the ID of the movie session.
   * 
   * @return The ID of the movie session.
   */
  public UUID getID() {
    return this.ID;
  }

  /**
   * Sets the ID of the movie session.
   * 
   * @param ID The ID of the movie session.
   */
  public void setID(UUID ID) {
    this.ID = ID;
  }

  /**
   * Returns the name of the movie.
   * 
   * @return The name of the movie.
   */
  public String getMovie() {
    return this.movie;
  }

  /**
   * Sets the name of the movie.
   * 
   * @param movie The name of the movie.
   */
  public void setMovie(String movie) {
    this.movie = movie;
  }

  /**
   * Returns the name of the cinema hall.
   * 
   * @return The name of the cinema hall.
   */
  public String getCinemaHall() {
    return this.cinemaHall;
  }

  /**
   * Sets the name of the cinema hall.
   * 
   * @param cinemaHall The name of the cinema hall.
   */
  public void setCinemaHall(String cinemaHall) {
    this.cinemaHall = cinemaHall;
  }

  /**
   * Returns the start time of the movie session.
   * 
   * @return The start time of the movie session.
   */
  public String getStartTime() {
    return this.startTime;
  }

  /**
   * Sets the start time of the movie session.
   * 
   * @param startTime The start time of the movie session.
   */
  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

}
