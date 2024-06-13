package com.cinema.main.views.movies;

import com.cinema.application.dtos.movies.CinemaHallDTO;

/**
 * The CinemaHallModel class represents the model for a cinema hall in the cinema management system.
 * It is responsible for managing the cinema hall data and providing access to it.
 */
public class CinemaHallModel {
  private static CinemaHallModel cinemaHallModel = null;
  private CinemaHallDTO cinemaHall;

  private CinemaHallModel() {
  }

  /**
   * Returns the singleton instance of the CinemaHallModel class.
   *
   * @return The singleton instance of the CinemaHallModel class.
   */
  public static CinemaHallModel getInstance() {
    if (cinemaHallModel == null) {
      cinemaHallModel = new CinemaHallModel();
    }

    return cinemaHallModel;
  }

  /**
   * Returns the cinema hall data.
   *
   * @return The cinema hall data.
   */
  public CinemaHallDTO getCinemaHall() {
    return cinemaHall;
  }

  /**
   * Sets the cinema hall data.
   *
   * @param cinemaHall The cinema hall data to be set.
   */
  public void setCinemaHall(CinemaHallDTO cinemaHall) {
    this.cinemaHall = cinemaHall;
  }
}
