package com.cinema.application.dtos.products;

import java.util.UUID;

import com.cinema.application.dtos.movies.MovieSessionDTO;

/**
 * Represents a data transfer object for ticket information.
 */
public class TicketInfosDTO {
  private UUID ID;
  private double price;
  private MovieSessionDTO movieSession;

  /**
   * Constructs a new TicketInfosDTO object with the specified ID, price, and movie session.
   * 
   * @param ID            The ID of the ticket.
   * @param price         The price of the ticket.
   * @param movieSession  The movie session associated with the ticket.
   */
  public TicketInfosDTO(UUID ID, double price, MovieSessionDTO movieSession) {
    this.ID = ID;
    this.price = price;
    this.movieSession = movieSession;
  }

  /**
   * Gets the ID of the ticket.
   * 
   * @return The ID of the ticket.
   */
  public UUID getID() {
    return this.ID;
  }

  /**
   * Sets the ID of the ticket.
   * 
   * @param ID The ID of the ticket.
   */
  public void setID(UUID ID) {
    this.ID = ID;
  }

  /**
   * Gets the price of the ticket.
   * 
   * @return The price of the ticket.
   */
  public String getPrice() {
    return "R$ " + String.valueOf(this.price);
  }

  /**
   * Sets the price of the ticket.
   * 
   * @param price The price of the ticket.
   */
  public void setPrice(double price) {
    this.price = price;
  }

  /**
   * Gets the movie session associated with the ticket.
   * 
   * @return The movie session associated with the ticket.
   */
  public MovieSessionDTO getMovieSession() {
    return this.movieSession;
  }

  /**
   * Sets the movie session associated with the ticket.
   * 
   * @param movieSession The movie session associated with the ticket.
   */
  public void setMovieSession(MovieSessionDTO movieSession) {
    this.movieSession = movieSession;
  }

  /**
   * Gets the cinema hall of the movie session.
   * 
   * @return The cinema hall of the movie session.
   */
  public String getCinemaHall() {
    return this.movieSession.getCinemaHall();
  }

  /**
   * Gets the movie of the movie session.
   * 
   * @return The movie of the movie session.
   */
  public String getMovie() {
    return this.movieSession.getMovie();
  }

  /**
   * Gets the start date of the movie session.
   * 
   * @return The start date of the movie session.
   */
  public String getStartDate() {
    String startDate = this.movieSession.getStartTime();

    return startDate.split("T")[1];
  }
}
