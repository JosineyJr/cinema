package com.cinema.domain.usecases.movies;

import java.util.List;

import com.cinema.domain.contracts.repositories.movies.IListCinemaHallRepository;
import com.cinema.domain.entities.movies.CinemaHall;

public class ListCinemaHallsUseCase {
  private IListCinemaHallRepository cinemaHallRepository;

  public ListCinemaHallsUseCase(IListCinemaHallRepository cinemaHallRepository) {
    this.cinemaHallRepository = cinemaHallRepository;
  }

  public List<CinemaHall> execute() {
    return cinemaHallRepository.listCinemaHalls();
  }
}
