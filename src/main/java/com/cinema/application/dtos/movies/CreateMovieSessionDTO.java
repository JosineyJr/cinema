package com.cinema.application.dtos.movies;

public class CreateMovieSessionDTO {
  private String movieID;
  private String cinemaHallID;
  private String startDate;
  private double ticketPrice;

  public CreateMovieSessionDTO(String movieID, String cinemaHallID, String startDate,
      double ticketPrice) {
    this.movieID = movieID;
    this.cinemaHallID = cinemaHallID;
    this.startDate = startDate;
    this.ticketPrice = ticketPrice;
  }

  public String getMovieID() {
    return this.movieID;
  }

  public void setMovieID(String movieID) {
    this.movieID = movieID;
  }

  public String getCinemaHallID() {
    return this.cinemaHallID;
  }

  public void setCinemaHallID(String cinemaHallID) {
    this.cinemaHallID = cinemaHallID;
  }

  public double getTicketPrice() {
    return this.ticketPrice;
  }

  public void setTicketPrice(double ticketPrice) {
    this.ticketPrice = ticketPrice;
  }

  public String getStartDate() {
    return this.startDate;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
}
