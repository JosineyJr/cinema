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

  @Column(nullable = false, unique = true)
  private String title;

  @Column(nullable = false)
  private String synopsis;

  @Column(nullable = false)
  private String director;

  @ManyToOne
  @JoinColumn(name = "genre_id", nullable = false)
  private PgGenre genre;

  @Column(nullable = false)
  private int duration;

  @Column(name = "minimum_age", nullable = false)
  private int minimumAge;

  public PgMovie() {
  }

  public PgMovie(UUID ID, String title, String synopsis, String director, PgGenre genre, int duration, int minimumAge) {
    this.ID = ID;
    this.title = title;
    this.synopsis = synopsis;
    this.director = director;
    this.genre = genre;
    this.duration = duration;
    this.minimumAge = minimumAge;
  }

  public PgMovie(String title, String synopsis, String director, PgGenre genre, int duration, int minimumAge) {
    this.title = title;
    this.synopsis = synopsis;
    this.director = director;
    this.genre = genre;
    this.duration = duration;
    this.minimumAge = minimumAge;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSynopsis() {
    return this.synopsis;
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  public String getDirector() {
    return this.director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public PgGenre getGenre() {
    return this.genre;
  }

  public void setGenre(PgGenre genre) {
    this.genre = genre;
  }

  public int getDuration() {
    return this.duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public int getMinimumAge() {
    return this.minimumAge;
  }

  public void setMinimumAge(int minimumAge) {
    this.minimumAge = minimumAge;
  }
}
