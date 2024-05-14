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

public class CreateCinemaHallController extends Controller {
  private CreateCinemaHallUseCase createCinemaHallUseCase;

  public CreateCinemaHallController(CreateCinemaHallUseCase createCinemaHallUseCase) {
    this.createCinemaHallUseCase = createCinemaHallUseCase;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Response perform(Object object) {
    try {
      CreateCinemaHallDTO createCinemaHallDTO = (CreateCinemaHallDTO) object;

      this.createCinemaHallUseCase.execute(createCinemaHallDTO.getNumberOfChairs(),
      createCinemaHallDTO.getName());

      return ResponseFactory.noContent();
    } catch (CinemaHallAlreadyExistsError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    CreateCinemaHallDTO createCinemaHallDTO = (CreateCinemaHallDTO) object;

    Field numberOfChairs = new Field(String.valueOf(createCinemaHallDTO.getNumberOfChairs()), "Número de Cadeiras");
    Field name = new Field(createCinemaHallDTO.getName(), "Nome");

    ArrayList<Field> requiredFields = new ArrayList<>(Arrays.asList(numberOfChairs, name));

    ArrayList<IValidator> validators = new ArrayList<>();

    validators.addAll(ValidationBuilder.of().required(requiredFields).minValue(name, 1).build());

    return validators;
  }
}
