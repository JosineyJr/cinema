package com.cinema.domain.usecases.movies;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.movies.IDeleteMovieSessionRepository;
import com.cinema.domain.contracts.repositories.movies.IFindMovieSessionByIdRepository;
import com.cinema.domain.errors.movies.MovieSessionNotFoundError;

public class DeleteMovieSessionUseCase {
  private IFindMovieSessionByIdRepository findMovieSessionByIdRepository;
  private IDeleteMovieSessionRepository deleteMovieSessionRepository;

  public DeleteMovieSessionUseCase(IFindMovieSessionByIdRepository findMovieSessionByIdRepository,
      IDeleteMovieSessionRepository deleteMovieSessionRepository) {
    this.findMovieSessionByIdRepository = findMovieSessionByIdRepository;
    this.deleteMovieSessionRepository = deleteMovieSessionRepository;
  }

  /**
   * Executes the use case to delete a movie session by its ID.
   *
   * @param ID the ID of the movie session to be deleted
   * @throws MovieSessionNotFoundError if the movie session with the given ID is not found
   */
  public void execute(UUID ID) throws MovieSessionNotFoundError {
    if (this.findMovieSessionByIdRepository.findMovieSessionById(ID) == null) {
      throw new MovieSessionNotFoundError();
    }

    this.deleteMovieSessionRepository.deleteMovieSession(ID);
  }
}
