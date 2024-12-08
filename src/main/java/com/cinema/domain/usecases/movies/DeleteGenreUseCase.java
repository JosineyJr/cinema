package com.cinema.domain.usecases.movies;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.IDeleteGenreRepository;
import com.cinema.domain.contracts.repositories.movies.IFindGenreByIDRepository;
import com.cinema.domain.errors.movies.GenreNotFoundError;

public class DeleteGenreUseCase {
  private IDeleteGenreRepository deleteGenreRepository;
  private IFindGenreByIDRepository findGenreByIDRepository;

  public DeleteGenreUseCase(IDeleteGenreRepository deleteGenreRepository,
      IFindGenreByIDRepository findGenreByIDRepository) {
    this.deleteGenreRepository = deleteGenreRepository;
    this.findGenreByIDRepository = findGenreByIDRepository;
  }

  /**
   * Executes the use case to delete a genre by its ID.
   *
   * @param ID the ID of the genre to be deleted
   * @throws GenreNotFoundError if the genre with the specified ID is not found
   */
  public void execute(UUID ID) throws GenreNotFoundError {
    if (this.findGenreByIDRepository.findGenreByID(ID) == null)
      throw new GenreNotFoundError();

    this.deleteGenreRepository.deleteGenre(ID);
  }
}
