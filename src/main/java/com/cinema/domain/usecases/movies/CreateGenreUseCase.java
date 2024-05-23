package com.cinema.domain.usecases.movies;

import com.cinema.domain.contracts.repositories.movies.ICreateGenreRepository;
import com.cinema.domain.contracts.repositories.movies.IFindGenreByNameRepository;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.errors.movies.GenreAlreadyExistsError;

public class CreateGenreUseCase {
  private IFindGenreByNameRepository findGenreByNameRepository;
  private ICreateGenreRepository createGenreRepository;

  public CreateGenreUseCase(IFindGenreByNameRepository findGenreByNameRepository,
      ICreateGenreRepository createGenreRepository) {
    this.findGenreByNameRepository = findGenreByNameRepository;
    this.createGenreRepository = createGenreRepository;
  }

  public void execute(String name) throws GenreAlreadyExistsError {
    Genre genreIsExists = this.findGenreByNameRepository.findGenreByName(name);

    if (genreIsExists != null) {
      throw new GenreAlreadyExistsError();
    }

    Genre genre = new Genre(name);

    this.createGenreRepository.createGenre(genre);
  }

}
