package com.cinema.domain.contracts.repositories.movies;

import java.util.UUID;

public interface IDeleteGenreRepository {
  public void deleteGenre(UUID ID);
}
