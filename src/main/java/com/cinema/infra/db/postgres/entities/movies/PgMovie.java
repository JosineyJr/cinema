package com.cinema.infra.db.postgres.entities.movies;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "movie")
public class PgMovie {
  @Id
  @GeneratedValue
  private UUID ID;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String director;
  
  @ManyToOne
  @JoinColumn(name = "genre_id", nullable = false)
  private PgGenre genre;

  @Column(nullable = false)
  private int duration;

  @Column(nullable = false)
  private int minimumAge;

    public PgMovie(UUID ID, String title, String description, String director, PgGenre genre, int duration, int minimumAge) {
    this.ID = ID;
    this.title = title;
    this.description = description;
    this.director = director;
    this.genre = genre;
    this.duration = duration;
    this.minimumAge = minimumAge;
  }

  public PgMovie(String title, String description, String director, PgGenre genre, int duration, int minimumAge) {
    this.title = title;
    this.description = description;
    this.director = director;
    this.genre = genre;
    this.duration = duration;
    this.minimumAge = minimumAge;
  }
}
