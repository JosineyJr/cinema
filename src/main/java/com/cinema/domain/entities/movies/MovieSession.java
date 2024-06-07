package com.cinema.domain.entities.movies;

import java.time.LocalTime;
import java.util.UUID;

public class MovieSession {
  private UUID ID;
  private Movie movie;
  private CinemaHall cinemaHall;
  private LocalTime startTime;

  public MovieSession(UUID ID, Movie movie, CinemaHall cinemaHall, LocalTime startTime) {
    this.ID = ID;
    this.movie = movie;
    this.cinemaHall = cinemaHall;
    this.startTime = startTime;
  }

  public MovieSession(Movie movie, CinemaHall cinemaHall, LocalTime startTime) {
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

  public Movie getMovie() {
    return this.movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public CinemaHall getCinemaHall() {
    return this.cinemaHall;
  }

  public void setCinemaHall(CinemaHall cinemaHall) {
    this.cinemaHall = cinemaHall;
  }

  public LocalTime getStartTime() {
    return this.startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }
}
