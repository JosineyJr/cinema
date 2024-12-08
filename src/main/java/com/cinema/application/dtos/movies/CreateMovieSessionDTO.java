package com.cinema.application.dtos.movies;

/**
 * Represents a data transfer object for creating a movie session.
 */
public class CreateMovieSessionDTO {
  private String movieID;
  private String cinemaHallID;
  private String startDate;
  private double ticketPrice;

  /**
   * Constructs a CreateMovieSessionDTO object with the specified parameters.
   *
   * @param movieID      the ID of the movie
   * @param cinemaHallID the ID of the cinema hall
   * @param startDate    the start date of the movie session
   * @param ticketPrice  the ticket price for the movie session
   */
  public CreateMovieSessionDTO(String movieID, String cinemaHallID, String startDate,
      double ticketPrice) {
    this.movieID = movieID;
    this.cinemaHallID = cinemaHallID;
    this.startDate = startDate;
    this.ticketPrice = ticketPrice;
  }

  /**
   * Gets the ID of the movie.
   *
   * @return the movie ID
   */
  public String getMovieID() {
    return this.movieID;
  }

  /**
   * Sets the ID of the movie.
   *
   * @param movieID the movie ID to set
   */
  public void setMovieID(String movieID) {
    this.movieID = movieID;
  }

  /**
   * Gets the ID of the cinema hall.
   *
   * @return the cinema hall ID
   */
  public String getCinemaHallID() {
    return this.cinemaHallID;
  }

  /**
   * Sets the ID of the cinema hall.
   *
   * @param cinemaHallID the cinema hall ID to set
   */
  public void setCinemaHallID(String cinemaHallID) {
    this.cinemaHallID = cinemaHallID;
  }

  /**
   * Gets the ticket price for the movie session.
   *
   * @return the ticket price
   */
  public double getTicketPrice() {
    return this.ticketPrice;
  }

  /**
   * Sets the ticket price for the movie session.
   *
   * @param ticketPrice the ticket price to set
   */
  public void setTicketPrice(double ticketPrice) {
    this.ticketPrice = ticketPrice;
  }

  /**
   * Gets the start date of the movie session.
   *
   * @return the start date
   */
  public String getStartDate() {
    return this.startDate;
  }

  /**
   * Sets the start date of the movie session.
   *
   * @param startDate the start date to set
   */
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
}
