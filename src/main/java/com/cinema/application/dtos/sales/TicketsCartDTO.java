package com.cinema.application.dtos.sales;

import java.util.UUID;

public class TicketsCartDTO {
  private UUID ID;
  private String movie;
  private String cinemaHall;
  private double price;
  private String startDate;
  private UUID personID;

  public TicketsCartDTO(UUID ID, String movie, String cinemaHall, double price, String startDate, UUID personID) {
    this.ID = ID;
    this.movie = movie;
    this.cinemaHall = cinemaHall;
    this.price = price;
    this.startDate = startDate;
    this.personID = personID;
  }

  public TicketsCartDTO() {
  }

  public TicketsCartDTO(UUID ID, UUID personID) {
    this.ID = ID;
    this.personID = personID;
  }

  public UUID getID() {
    return ID;
  }

  public String getMovie() {
    return movie;
  }

  public String getCinemaHall() {
    return cinemaHall;
  }

  public double getPrice() {
    return price;
  }

  public String getStartDate() {
    return startDate;
  }

  public UUID getPersonID() {
    return personID;
  }

  public void setMovie(String movie) {
    this.movie = movie;
  }

  public void setCinemaHall(String cinemaHall) {
    this.cinemaHall = cinemaHall;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void setPersonID(UUID personID) {
    this.personID = personID;
  }
}
