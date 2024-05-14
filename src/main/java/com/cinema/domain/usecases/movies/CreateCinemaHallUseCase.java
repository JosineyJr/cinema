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

  public void execute(int capacity, String name) throws CinemaHallAlreadyExistsError {
    CinemaHall cinemaHallAlreadyExists = this.findCinemaHallByNameRepository.findCinemaHallByName(name);

    if (cinemaHallAlreadyExists != null) {
      throw new CinemaHallAlreadyExistsError();
    }

    CinemaHall cinemaHall = new CinemaHall(capacity, name);

    this.createCinemaHallRepository.createCinemaHall(cinemaHall);
  }
}
