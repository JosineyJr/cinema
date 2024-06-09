package com.cinema.application.dtos.products;

import java.util.UUID;

import com.cinema.application.dtos.movies.MovieSessionDTO;

public class TicketDTO {
  private UUID ID;
  private double price;
  private MovieSessionDTO movieSession;

  public TicketDTO(UUID ID, double price, MovieSessionDTO movieSession) {
    this.ID = ID;
    this.price = price;
    this.movieSession = movieSession;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public String getPrice() {
    return "R$ " + String.valueOf(this.price);
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public MovieSessionDTO getMovieSession() {
    return this.movieSession;
  }

  public void setMovieSession(MovieSessionDTO movieSession) {
    this.movieSession = movieSession;
  }

  public String getCinemaHall() {
    return this.movieSession.getCinemaHall();
  }

  public String getMovie() {
    return this.movieSession.getMovie();
  }

  public String getStartDate() {
    String startDate = this.movieSession.getStartTime();

    return startDate.split("T")[1];

  }

}
