package com.cinema.application.dtos.movies;

public class CreateMovieSessionDTO {
  private String movieID;
  private String cinemaHallID;
  private String startTime;

  public CreateMovieSessionDTO(String movieID, String cinemaHallID, String startTime) {
    this.movieID = movieID;
    this.cinemaHallID = cinemaHallID;
    this.startTime = startTime;
  }

  public String getMovieID() {
    return this.movieID;
  }

  public void setMovieID(String movieID) {
    this.movieID = movieID;
  }

  public String getCinemaHallID() {
    return this.cinemaHallID;
  }

  public void setCinemaHallID(String cinemaHallID) {
    this.cinemaHallID = cinemaHallID;
  }

  public String getStartTime() {
    return this.startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }
}
