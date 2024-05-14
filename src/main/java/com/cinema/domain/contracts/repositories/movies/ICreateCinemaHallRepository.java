package com.cinema.domain.contracts.repositories.movies;

import com.cinema.domain.entities.movies.CinemaHall;

public interface ICreateCinemaHallRepository {
  public void createCinemaHall(CinemaHall cinemaHall);
}
