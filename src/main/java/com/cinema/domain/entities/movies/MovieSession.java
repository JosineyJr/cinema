package com.cinema.domain.entities.movies;

import java.time.LocalDateTime;
import java.util.UUID;

public class MovieSession {
  private UUID ID;
  private Movie movie;
  private CinemaHall cinemaHall;
  private LocalDateTime startDate;

  public MovieSession(UUID ID, Movie movie, CinemaHall cinemaHall, LocalDateTime startDate) {
    this.ID = ID;
    this.movie = movie;
    this.cinemaHall = cinemaHall;
    this.startDate = startDate;
  }

  public MovieSession(Movie movie, CinemaHall cinemaHall, LocalDateTime startDate) {
    this.movie = movie;
    this.cinemaHall = cinemaHall;
    this.startDate = startDate;
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

  public LocalDateTime getStartDate() {
    return this.startDate;
  }

  public void setStartDate(LocalDateTime startDate) {
    this.startDate = startDate;
  }
}
