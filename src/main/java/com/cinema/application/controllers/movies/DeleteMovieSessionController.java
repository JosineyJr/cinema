package com.cinema.application.controllers.movies;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.DeleteMovieSessionDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.errors.movies.MovieSessionNotFoundError;
import com.cinema.domain.usecases.movies.DeleteMovieSessionUseCase;

public class DeleteMovieSessionController extends Controller<DeleteMovieSessionDTO> {
  private DeleteMovieSessionUseCase deleteMovieSessionUseCase;

  public DeleteMovieSessionController(DeleteMovieSessionUseCase deleteMovieSessionUseCase) {
    this.deleteMovieSessionUseCase = deleteMovieSessionUseCase;
  }

  /**
   * Performs the deletion of a movie session based on the provided DeleteMovieSessionDTO.
   *
   * @param deleteMovieSessionDTO The DeleteMovieSessionDTO containing the ID of the movie session to be deleted.
   * @return A Response object indicating the result of the deletion operation.
   */
  public Response<?> perform(DeleteMovieSessionDTO deleteMovieSessionDTO) {
    try {
      this.deleteMovieSessionUseCase.execute(deleteMovieSessionDTO.getID());

      return ResponseFactory.noContent();
    } catch (MovieSessionNotFoundError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given DeleteMovieSessionDTO object.
   *
   * @param object The DeleteMovieSessionDTO object for which validators need to be built.
   * @return An ArrayList of IValidator objects.
   */
  public ArrayList<IValidator> buildValidators(DeleteMovieSessionDTO object) {
    return new ArrayList<IValidator>();
  }
}
