package com.cinema.domain.usecases.movies;

import java.util.List;

import com.cinema.domain.contracts.repositories.movies.IListGenresRepository;
import com.cinema.domain.entities.movies.Genre;

public class ListGenresUseCase {
  private IListGenresRepository genresRepository;

  public ListGenresUseCase(IListGenresRepository genresRepository) {
    this.genresRepository = genresRepository;
  }

  /**
   * Executes the use case to list all genres.
   *
   * @return a list of Genre objects representing the available genres.
   */
  public List<Genre> execute() {
    return genresRepository.listGenres();
  }
}
