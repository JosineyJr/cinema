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

  @Column(name = "is_half_price", nullable = false)
  private boolean isHalfPrice;
}
