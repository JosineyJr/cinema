package com.cinema.domain.usecases.movies;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.IDeleteMovieRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieByIDRepository;
import com.cinema.domain.errors.movies.MovieNotFoundError;

public class DeleteMovieUseCase {
  private IDeleteMovieRepository deleteMovieRepository;
  private IFindMovieByIDRepository findMovieByIDRepository;

  public DeleteMovieUseCase(IDeleteMovieRepository deleteMovieRepository,
      IFindMovieByIDRepository findMovieByIDRepository) {
    this.deleteMovieRepository = deleteMovieRepository;
    this.findMovieByIDRepository = findMovieByIDRepository;
  }

  /**
   * Executes the use case to delete a movie by its ID.
   *
   * @param ID the ID of the movie to be deleted
   * @throws MovieNotFoundError if the movie with the given ID is not found
   */
  public void execute(UUID ID) throws MovieNotFoundError {
    if (this.findMovieByIDRepository.findMovieByID(ID) == null) {
      throw new MovieNotFoundError();
    }

    this.deleteMovieRepository.deleteMovie(ID);
  }
}
