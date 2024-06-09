package com.cinema.application.controllers.movies;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.DeleteMovieDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.errors.movies.MovieNotFoundError;
import com.cinema.domain.usecases.movies.DeleteMovieUseCase;

public class DeleteMovieController extends Controller<DeleteMovieDTO> {
  private DeleteMovieUseCase deleteMovieUseCase;

  public DeleteMovieController(DeleteMovieUseCase deleteMovieUseCase) {
    this.deleteMovieUseCase = deleteMovieUseCase;
  }

  /**
   * Performs the deletion of a movie based on the provided DeleteMovieDTO.
   *
   * @param deleteMovieDTO The DeleteMovieDTO containing the ID of the movie to be deleted.
   * @return A Response object representing the result of the deletion operation.
   */
  public Response<?> perform(DeleteMovieDTO deleteMovieDTO) {
    try {
      this.deleteMovieUseCase.execute(deleteMovieDTO.getID());

      return ResponseFactory.noContent();
    } catch (MovieNotFoundError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given DeleteMovieDTO object.
   *
   * @param object The DeleteMovieDTO object for which validators need to be built.
   * @return An ArrayList of IValidator objects representing the validators for the DeleteMovieDTO object.
   */
  public ArrayList<IValidator> buildValidators(DeleteMovieDTO object) {
    return new ArrayList<IValidator>();
  }
}
