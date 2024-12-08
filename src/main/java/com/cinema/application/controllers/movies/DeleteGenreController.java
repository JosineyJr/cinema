package com.cinema.application.controllers.movies;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.DeleteGenreDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.errors.movies.GenreNotFoundError;
import com.cinema.domain.usecases.movies.DeleteGenreUseCase;

public class DeleteGenreController extends Controller<DeleteGenreDTO> {
  private DeleteGenreUseCase deleteGenreUseCase;

  public DeleteGenreController(DeleteGenreUseCase deleteGenreUseCase) {
    this.deleteGenreUseCase = deleteGenreUseCase;
  }

  /**
   * Performs the deletion of a genre based on the provided DeleteGenreDTO.
   *
   * @param deleteGenreDTO The DeleteGenreDTO containing the ID of the genre to be
   *                       deleted.
   * @return A Response object indicating the success or failure of the deletion
   *         operation.
   */
  public Response<?> perform(DeleteGenreDTO deleteGenreDTO) {
    try {
      this.deleteGenreUseCase.execute(deleteGenreDTO.getID());

      return ResponseFactory.noContent();
    } catch (GenreNotFoundError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given DeleteGenreDTO object.
   *
   * @param object The DeleteGenreDTO object for which validators need to be built.
   * @return An ArrayList of IValidator objects representing the validators for the DeleteGenreDTO object.
   */
  public ArrayList<IValidator> buildValidators(DeleteGenreDTO object) {
    return new ArrayList<IValidator>();
  }
}
