package com.cinema.application.dtos.movies;

import java.util.UUID;

public class MovieSessionDTO {
  private UUID ID;
  private String movie;
  private String cinemaHall;
  private String startTime;

  public MovieSessionDTO(UUID ID, String movie, String cinemaHall, String startTime) {
    this.ID = ID;
    this.movie = movie;
    this.cinemaHall = cinemaHall;
    this.startTime = startTime;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public String getMovie() {
    return this.movie;
  }

  public void setMovie(String movie) {
    this.movie = movie;
  }

  public String getCinemaHall() {
    return this.cinemaHall;
  }

  public void setCinemaHall(String cinemaHall) {
    this.cinemaHall = cinemaHall;
  }

  public String getStartTime() {
    return this.startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

}
