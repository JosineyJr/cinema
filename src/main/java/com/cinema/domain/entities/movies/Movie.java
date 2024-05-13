package com.cinema.domain.entities.movies;

import java.util.UUID;

public class Movie {
  private UUID ID;
  private String title;
  private String description;
  private String director;
  private Genre genre;
  private int duration;
  private int minimumAge;

  public Movie(UUID ID, String title, String description, String director, Genre genre, int duration, int minimumAge) {
    this.ID = ID;
    this.title = title;
    this.description = description;
    this.director = director;
    this.genre = genre;
    this.duration = duration;
    this.minimumAge = minimumAge;
  }

  public Movie(String title, String description, String director, Genre genre, int duration, int minimumAge) {
    this.title = title;
    this.description = description;
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

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDirector() {
    return this.director;
  }

  public void setDirector(String director) {
    this.director = director;
  }

  public Genre getGenre() {
    return this.genre;
  }

  public void setGenre(Genre genre) {
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
