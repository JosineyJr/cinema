package com.cinema.application.controllers.movies;

import java.util.ArrayList;
import java.util.Arrays;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.CreateGenreDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.errors.movies.GenreAlreadyExistsError;
import com.cinema.domain.usecases.movies.CreateGenreUseCase;

public class CreateGenreController extends Controller<CreateGenreDTO> {
  private CreateGenreUseCase createGenreUseCase;

  public CreateGenreController(CreateGenreUseCase createGenreUseCase) {
    this.createGenreUseCase = createGenreUseCase;
  }

  @Override
  public Response<?> perform(CreateGenreDTO object) {
    try {

      this.createGenreUseCase.execute(object.getName());

      return ResponseFactory.noContent();
    } catch (GenreAlreadyExistsError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(CreateGenreDTO object) {

    Field name = new Field(object.getName(), "Nome");

    ArrayList<Field> requireFields = new ArrayList<>(Arrays.asList(name));

    ArrayList<IValidator> validators = new ArrayList<>();

    validators.addAll(ValidationBuilder.of().required(requireFields).build());

    return validators;
  }

}
