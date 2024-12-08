package com.cinema.domain.usecases.movies;

import com.cinema.domain.contracts.repositories.movies.IUpdateGenreRepository;
import com.cinema.domain.entities.movies.Genre;

public class UpdateGenreUseCase {
  private IUpdateGenreRepository updateGenreRepository;

  public UpdateGenreUseCase(IUpdateGenreRepository updateGenreRepository) {
    this.updateGenreRepository = updateGenreRepository;
  }

  public void execute(Genre genre) {
    this.updateGenreRepository.updateGenre(genre);
  }
}
