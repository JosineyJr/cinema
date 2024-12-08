package com.cinema.domain.contracts.repositories.movies;

import java.util.UUID;

public interface IDeleteCinemaHallRepository {
  public void deleteCinemaHall(UUID cinemaHallId);
}
