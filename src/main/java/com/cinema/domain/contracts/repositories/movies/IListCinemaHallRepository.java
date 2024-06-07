package com.cinema.domain.contracts.repositories.movies;

import java.util.List;

import com.cinema.domain.entities.movies.CinemaHall;

public interface IListCinemaHallRepository {
  public List<CinemaHall> listCinemaHalls();
}
