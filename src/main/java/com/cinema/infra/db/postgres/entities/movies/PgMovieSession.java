package com.cinema.infra.db.postgres.entities.movies;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "movie_session")
public class PgMovieSession {
  @Id
  @GeneratedValue
  private UUID ID;

  @ManyToOne
  @JoinColumn(name = "movie_id", nullable = false)
  private PgMovie movie;

  @ManyToOne
  @JoinColumn(name = "cinema_hall_id", nullable = false)
  private PgCinemaHall cinemaHall;

  @Column(name = "start_time", nullable = false)
  private LocalDateTime startTime;

  public PgMovieSession() {
  }

  public PgMovieSession(UUID ID, PgMovie movie, PgCinemaHall cinemaHall, LocalDateTime startTime) {
    this.ID = ID;
    this.movie = movie;
    this.cinemaHall = cinemaHall;
    this.startTime = startTime;
  }

  public PgMovieSession(PgMovie movie, PgCinemaHall cinemaHall, LocalDateTime startTime) {
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

  public PgMovie getMovie() {
    return this.movie;
  }

  public void setMovie(PgMovie movie) {
    this.movie = movie;
  }

  public PgCinemaHall getCinemaHall() {
    return this.cinemaHall;
  }

  public void setCinemaHall(PgCinemaHall cinemaHall) {
    this.cinemaHall = cinemaHall;
  }

  public LocalDateTime getStartTime() {
    return this.startTime;
  }

  public void setStartTime(LocalDateTime startTime) {
    this.startTime = startTime;
  }
}
