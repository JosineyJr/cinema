package com.cinema.application.dtos.movies;

import java.util.UUID;

import com.cinema.domain.entities.movies.Genre;

public class MovieDTO {
  private UUID ID;
  private String title;
  private String synopsis;
  private String director;
  private Genre genre;
  private int duration;
  private int minimumAge;

  public MovieDTO(UUID ID, String title, String synopsis, String director, Genre genre, int duration, int minimumAge) {
    this.ID = ID;
    this.title = title;
    this.synopsis = synopsis;
    this.director = director;
    this.genre = genre;
    this.duration = duration;
    this.minimumAge = minimumAge;
  }

  public MovieDTO(String title, String synopsis, String director, Genre genre, int duration, int minimumAge) {
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
