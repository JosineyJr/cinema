package com.cinema.application.dtos.movies;

public class UpdateMovieDTO {
  private String ID;
  private String title;
  private String director;
  private String synopsis;
  private String genreID;
  private int duration;
  private int minimumAge;

  public UpdateMovieDTO(String ID, String title, String director, String synopsis, String genreID, int duration,
      int minimumAge) {
    this.ID = ID;
    this.title = title;
    this.director = director;
    this.synopsis = synopsis;
    this.genreID = genreID;
    this.duration = duration;
    this.minimumAge = minimumAge;
  }

  public String getID() {
    return ID;
  }

  public String getTitle() {
    return title;
  }

  public String getDirector() {
    return director;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public String getGenreID() {
    return genreID;
  }

  public int getDuration() {
    return duration;
  }

  public int getMinimumAge() {
    return minimumAge;
  }
}
