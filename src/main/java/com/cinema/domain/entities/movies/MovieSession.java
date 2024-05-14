package com.cinema.domain.entities.movies;

import java.time.LocalDateTime;
import java.util.UUID;

public class MovieSession {
  private UUID ID;
  private Movie movie;
  private CinemaHall cinemaHall;
  private LocalDateTime startTime;

  public static final int BREAK_TIME_BETWEEN_SESSIONS = 20; //minutes

  public MovieSession(UUID ID, Movie movie, CinemaHall cinemaHall, LocalDateTime startTime) {
    this.ID = ID;
    this.movie = movie;
    this.cinemaHall = cinemaHall;
    this.startTime = startTime;
  }

  public MovieSession(Movie movie, CinemaHall cinemaHall, LocalDateTime startTime) {
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

  public LocalDateTime getStartTime() {
    return this.startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }
}
