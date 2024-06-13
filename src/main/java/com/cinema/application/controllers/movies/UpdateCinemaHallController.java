package com.cinema.application.controllers.movies;

import java.util.ArrayList;
import java.util.Arrays;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.UpdateCinemaHallDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.domain.errors.movies.CinemaHallNotFoundError;
import com.cinema.domain.usecases.movies.UpdateCinemaHallUseCase;

public class UpdateCinemaHallController extends Controller<UpdateCinemaHallDTO> {
  private UpdateCinemaHallUseCase updateCinemaHallUseCase;

  public UpdateCinemaHallController(UpdateCinemaHallUseCase updateCinemaHallUseCase) {
    this.updateCinemaHallUseCase = updateCinemaHallUseCase;
  }

  public Response<?> perform(UpdateCinemaHallDTO updateCinemaHallDTO) {
    try {
      CinemaHall cinemaHall = new CinemaHall(updateCinemaHallDTO.getID(), updateCinemaHallDTO.getCapacity(),
          updateCinemaHallDTO.getName());

      this.updateCinemaHallUseCase.execute(cinemaHall);

      return ResponseFactory.noContent();
    } catch (CinemaHallNotFoundError e) {
      return ResponseFactory.badRequest(e);
    } catch (Exception e) {
      return ResponseFactory.serverError(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given UpdateCinemaHallDTO
   * object.
   *
   * @param object The UpdateCinemaHallDTO object to be validated.
   * @return An ArrayList of IValidator objects representing the validators for
   *         the given object.
   */
  public ArrayList<IValidator> buildValidators(UpdateCinemaHallDTO object) {
    Field cinemaHallID = new Field(object.getID().toString(), "ID");
    Field cinemaHallName = new Field(object.getName(), "Nome");
    Field cinemaHallCapacity = new Field(String.valueOf(object.getCapacity()), "Capacidade");

    ArrayList<Field> requiredFields = new ArrayList<>(
        Arrays.asList(cinemaHallID, cinemaHallName, cinemaHallCapacity));

    ArrayList<IValidator> validators = new ArrayList<>();

    validators.addAll(ValidationBuilder.of().required(requiredFields).validateUUID(cinemaHallID)
        .minValue(cinemaHallCapacity, 1).build());

    return validators;
  }
}
