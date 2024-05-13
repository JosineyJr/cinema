package com.cinema.application.dtos.movies;

public class CreateMovieDTO {
  private String title;
  private String description;
  private String director;
  private String genreID;
  private int duration;
  private int minimumAge;

  public CreateMovieDTO(String title, String description, String director, String genreID, int duration, int minimumAge) {
    this.title = title;
    this.description = description;
    this.director = director;
    this.genreID = genreID;
    this.duration = duration;
    this.minimumAge = minimumAge;
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

  public String getGenreID() {
    return this.genreID;
  }

  public void setGenreID(String genreID) {
    this.genreID = genreID;
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
