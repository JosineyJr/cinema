package com.cinema.application.controllers.movies;

import java.util.ArrayList;
import java.util.Arrays;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.CreateCinemaHallDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.errors.movies.CinemaHallAlreadyExistsError;
import com.cinema.domain.usecases.movies.CreateCinemaHallUseCase;

public class CreateCinemaHallController extends Controller<CreateCinemaHallDTO> {
  private CreateCinemaHallUseCase createCinemaHallUseCase;

  public CreateCinemaHallController(CreateCinemaHallUseCase createCinemaHallUseCase) {
    this.createCinemaHallUseCase = createCinemaHallUseCase;
  }

  @Override
  public Response<?> perform(CreateCinemaHallDTO object) {
    try {

      this.createCinemaHallUseCase.execute(object.getCapacity(),
          object.getName());

      return ResponseFactory.noContent();
    } catch (CinemaHallAlreadyExistsError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(CreateCinemaHallDTO object) {

    Field capacity = new Field(String.valueOf(object.getCapacity()), "Número de Cadeiras");
    Field name = new Field(object.getName(), "Nome");

    ArrayList<Field> requiredFields = new ArrayList<>(Arrays.asList(capacity, name));

    ArrayList<IValidator> validators = new ArrayList<>();

    validators.addAll(ValidationBuilder.of().required(requiredFields).minValue(capacity, 1).build());

    return validators;
  }
}
