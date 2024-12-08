package com.cinema.infra.db.postgres.entities.products;

import java.util.UUID;

import com.cinema.infra.db.postgres.entities.movies.PgMovieSession;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "ticket")
public class PgTicket {
  @Id
  @GeneratedValue
  private UUID ID;

  @Column
  private double price;

  @ManyToOne
  @JoinColumn(name = "movie_session_id", nullable = false)
  private PgMovieSession movieSession;

  public PgTicket() {
  }

  public PgTicket(UUID ID, double price, PgMovieSession movieSession) {
    this.ID = ID;
    this.price = price;
    this.movieSession = movieSession;
  }

  public PgTicket(double price, PgMovieSession movieSession) {
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

  public PgMovieSession getMovieSession() {
    return this.movieSession;
  }

  public void setMovieSession(PgMovieSession movieSession) {
    this.movieSession = movieSession;
  }

}
