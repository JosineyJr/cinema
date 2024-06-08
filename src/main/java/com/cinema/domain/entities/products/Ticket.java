package com.cinema.domain.entities.products;

import java.util.UUID;

import com.cinema.domain.entities.movies.MovieSession;

public class Ticket {
  private UUID ID;
  private double price;
  private MovieSession movieSession;

  public Ticket(UUID ID, double price, MovieSession movieSession) {
    this.ID = ID;
    this.price = price;
    this.movieSession = movieSession;
  }

  public Ticket(double price, MovieSession movieSession) {
    this.price = price;
    this.movieSession = movieSession;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public MovieSession getMovieSession() {
    return this.movieSession;
  }

  public void setMovieSession(MovieSession movieSession) {
    this.movieSession = movieSession;
  }
}
