package com.cinema.domain.usecases.movies;

import java.util.List;

import com.cinema.domain.contracts.repositories.movies.IListCinemaHallRepository;
import com.cinema.domain.entities.movies.CinemaHall;

public class ListCinemaHallsUseCase {
  private IListCinemaHallRepository cinemaHallRepository;

  public ListCinemaHallsUseCase(IListCinemaHallRepository cinemaHallRepository) {
    this.cinemaHallRepository = cinemaHallRepository;
  }

  /**
   * Executes the use case to list all cinema halls.
   *
   * @return a list of CinemaHall objects representing all the cinema halls.
   */
  public List<CinemaHall> execute() {
    return cinemaHallRepository.listCinemaHalls();
  }
}
