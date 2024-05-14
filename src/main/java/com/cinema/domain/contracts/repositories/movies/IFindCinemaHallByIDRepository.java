package com.cinema.domain.contracts.repositories.movies;

import java.util.UUID;

import com.cinema.domain.entities.movies.CinemaHall;

public interface IFindCinemaHallByIDRepository {
  public CinemaHall findCinemaHallByID(UUID ID);
}
