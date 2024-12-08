package com.cinema.domain.contracts.repositories.movies;

import com.cinema.domain.entities.movies.CinemaHall;

public interface IFindCinemaHallByNameRepository {
  public CinemaHall findCinemaHallByName(String name);
}
