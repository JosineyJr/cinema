package com.cinema.domain.usecases.movies;

import com.cinema.domain.contracts.repositories.movies.IFindCinemaHallByIDRepository;
import com.cinema.domain.contracts.repositories.movies.IUpdateCinemaHallRepository;
import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.domain.errors.movies.CinemaHallNotFoundError;

public class UpdateCinemaHallUseCase {
  private IFindCinemaHallByIDRepository findCinemaHallByIDRepository;
  private IUpdateCinemaHallRepository updateCinemaHallRepository;

  public UpdateCinemaHallUseCase(IFindCinemaHallByIDRepository findCinemaHallByIDRepository, IUpdateCinemaHallRepository updateCinemaHallRepository) {
    this.findCinemaHallByIDRepository = findCinemaHallByIDRepository;
    this.updateCinemaHallRepository = updateCinemaHallRepository;
  }

  /**
   * Executes the use case to update a cinema hall.
   *
   * @param cinemaHall The cinema hall to be updated.
   * @throws CinemaHallNotFoundError If the cinema hall is not found.
   */
  public void execute(CinemaHall cinemaHall) throws CinemaHallNotFoundError{
    CinemaHall foundCinemaHall = this.findCinemaHallByIDRepository.findCinemaHallByID(cinemaHall.getID());

    if (foundCinemaHall == null) {
      throw new CinemaHallNotFoundError();
    }

    this.updateCinemaHallRepository.updateCinemaHall(cinemaHall);
  }
}
