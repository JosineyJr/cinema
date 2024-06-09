package com.cinema.domain.entities.movies;

import java.time.LocalDateTime;
import java.util.UUID;

public class MovieSession {
  private UUID ID;
  private Movie movie;
  private CinemaHall cinemaHall;
  private LocalDateTime startDate;
  private int soldTickets;

  public MovieSession(UUID ID, Movie movie, CinemaHall cinemaHall, LocalDateTime startDate, int soldTickets) {
    this.ID = ID;
    this.movie = movie;
    this.cinemaHall = cinemaHall;
    this.startDate = startDate;
    this.soldTickets = soldTickets;
  }

  public MovieSession(Movie movie, CinemaHall cinemaHall, LocalDateTime startDate) {
    this.movie = movie;
    this.cinemaHall = cinemaHall;
    this.startDate = startDate;
    this.soldTickets = 0;
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

  public int getSoldTickets() {
    return this.soldTickets;
  }

  public void setSoldTickets(int soldTickets) {
    this.soldTickets = soldTickets;
  }

  public boolean ticketsAvailable() {
    return this.soldTickets < this.cinemaHall.getCapacity();
  }
}
