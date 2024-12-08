package com.cinema.domain.usecases.movies;

import com.cinema.domain.contracts.repositories.movies.ICreateCinemaHallRepository;
import com.cinema.domain.contracts.repositories.movies.IFindCinemaHallByNameRepository;
import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.domain.errors.movies.CinemaHallAlreadyExistsError;

public class CreateCinemaHallUseCase {
  private ICreateCinemaHallRepository createCinemaHallRepository;
  private IFindCinemaHallByNameRepository findCinemaHallByNameRepository;

  public CreateCinemaHallUseCase(ICreateCinemaHallRepository createCinemaHallRepository,
      IFindCinemaHallByNameRepository findCinemaHallByNameRepository) {
    this.createCinemaHallRepository = createCinemaHallRepository;
    this.findCinemaHallByNameRepository = findCinemaHallByNameRepository;
  }

  /**
   * Executes the use case to create a new cinema hall with the given capacity and name.
   *
   * @param capacity The capacity of the cinema hall.
   * @param name The name of the cinema hall.
   * @throws CinemaHallAlreadyExistsError If a cinema hall with the same name already exists.
   */
  public void execute(int capacity, String name) throws CinemaHallAlreadyExistsError {
    CinemaHall cinemaHallAlreadyExists = this.findCinemaHallByNameRepository.findCinemaHallByName(name);

    if (cinemaHallAlreadyExists != null) {
      throw new CinemaHallAlreadyExistsError();
    }

    CinemaHall cinemaHall = new CinemaHall(capacity, name);

    this.createCinemaHallRepository.createCinemaHall(cinemaHall);
  }
}
