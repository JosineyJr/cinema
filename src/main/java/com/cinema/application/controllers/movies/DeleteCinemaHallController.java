package com.cinema.application.controllers.movies;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.DeleteCinemaHallDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.errors.movies.CinemaHallNotFoundError;
import com.cinema.domain.usecases.movies.DeleteCinemaHallUseCase;

public class DeleteCinemaHallController extends Controller<DeleteCinemaHallDTO> {
  private DeleteCinemaHallUseCase deleteCinemaHallUseCase;

  public DeleteCinemaHallController(DeleteCinemaHallUseCase deleteCinemaHallUseCase) {
    this.deleteCinemaHallUseCase = deleteCinemaHallUseCase;
  }

  /**
   * Performs the deletion of a cinema hall.
   *
   * @param object The DeleteCinemaHallDTO object containing the ID of the cinema hall to be deleted.
   * @return A Response object indicating the success or failure of the deletion operation.
   */
  public Response<?> perform(DeleteCinemaHallDTO object) {
    try {
      this.deleteCinemaHallUseCase.execute(object.getID());

      return ResponseFactory.noContent();
    } catch (CinemaHallNotFoundError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given DeleteCinemaHallDTO object.
   *
   * @param object The DeleteCinemaHallDTO object for which validators need to be built.
   * @return An ArrayList of IValidator objects representing the validators for the given object.
   */
  public ArrayList<IValidator> buildValidators(DeleteCinemaHallDTO object) {
    return new ArrayList<IValidator>();
  }
}
