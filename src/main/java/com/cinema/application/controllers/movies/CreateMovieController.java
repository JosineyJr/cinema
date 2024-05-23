package com.cinema.application.controllers.movies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.CreateMovieDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.errors.movies.GenreNotFoundError;
import com.cinema.domain.usecases.movies.CreateMovieUseCase;

public class CreateMovieController extends Controller<CreateMovieDTO> {
  private CreateMovieUseCase createMovieUseCase;

  public CreateMovieController(CreateMovieUseCase createMovieUseCase) {
    this.createMovieUseCase = createMovieUseCase;
  }

  @Override
  public Response<?> perform(CreateMovieDTO object) {
    try {

      UUID genreUUID = UUID.fromString(object.getGenreID());

      this.createMovieUseCase.execute(
          object.getTitle(),
          object.getSynopsis(),
          object.getDirector(),
          genreUUID,
          object.getDuration(),
          object.getMinimumAge());

      return ResponseFactory.noContent();
    } catch (GenreNotFoundError e) {
      return ResponseFactory.badRequest(e);
    }

  }

  @Override
  public ArrayList<IValidator> buildValidators(CreateMovieDTO object) {

    Field title = new Field(object.getTitle(), "Título");
    Field synopsis = new Field(object.getSynopsis(), "Descrição");
    Field director = new Field(object.getDirector(), "Diretor");
    Field genreID = new Field(object.getGenreID(), "Gênero");
    Field duration = new Field(String.valueOf(object.getDuration()), "Duração");
    Field minimumAge = new Field(String.valueOf(object.getMinimumAge()), "Idade Mínima");

    ArrayList<Field> requiredFields = new ArrayList<>(
        Arrays.asList(title, synopsis, director, genreID, duration, minimumAge));

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    validators.addAll(ValidationBuilder.of().required(requiredFields).validateUUID(genreID).build());

    return validators;
  }
}
