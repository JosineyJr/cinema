package com.cinema.domain.usecases.movies;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.IDeleteCinemaHallRepository;
import com.cinema.domain.contracts.repositories.movies.IFindCinemaHallByIDRepository;
import com.cinema.domain.errors.movies.CinemaHallNotFoundError;

public class DeleteCinemaHallUseCase {
  private IDeleteCinemaHallRepository deleteCinemaHallRepository;
  private IFindCinemaHallByIDRepository findCinemaHallByIDRepository;

  public DeleteCinemaHallUseCase(IDeleteCinemaHallRepository deleteCinemaHallRepository,
      IFindCinemaHallByIDRepository findCinemaHallByIDRepository) {
    this.deleteCinemaHallRepository = deleteCinemaHallRepository;
    this.findCinemaHallByIDRepository = findCinemaHallByIDRepository;
  }

  public void execute(UUID cinemaHallId) throws CinemaHallNotFoundError{
    if (this.findCinemaHallByIDRepository.findCinemaHallByID(cinemaHallId) == null) {
      throw new CinemaHallNotFoundError();
    }

    this.deleteCinemaHallRepository.deleteCinemaHall(cinemaHallId);
  }
}
