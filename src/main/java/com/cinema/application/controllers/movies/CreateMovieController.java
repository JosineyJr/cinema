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

public class CreateMovieController extends Controller {
  private CreateMovieUseCase createMovieUseCase;

  public CreateMovieController(CreateMovieUseCase createMovieUseCase) {
    this.createMovieUseCase = createMovieUseCase;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Response perform(Object object) {
    try {
      CreateMovieDTO createMovieDTO = (CreateMovieDTO) object;

      UUID genreUUID = UUID.fromString(createMovieDTO.getGenreID());

      this.createMovieUseCase.execute(
          createMovieDTO.getTitle(),
          createMovieDTO.getSynopsis(),
          createMovieDTO.getDirector(),
          genreUUID,
          createMovieDTO.getDuration(),
          createMovieDTO.getMinimumAge());

      return ResponseFactory.noContent();
    } catch (GenreNotFoundError e) {
      return ResponseFactory.badRequest(e);
    }

  }

  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    CreateMovieDTO createMovieDTO = (CreateMovieDTO) object;

    Field title = new Field(createMovieDTO.getTitle(), "Título");
    Field synopsis = new Field(createMovieDTO.getSynopsis(), "Descrição");
    Field director = new Field(createMovieDTO.getDirector(), "Diretor");
    Field genreID = new Field(createMovieDTO.getGenreID(), "Gênero");
    Field duration = new Field(String.valueOf(createMovieDTO.getDuration()), "Duração");
    Field minimumAge = new Field(String.valueOf(createMovieDTO.getMinimumAge()), "Idade Mínima");

    ArrayList<Field> requiredFields = new ArrayList<>(
        Arrays.asList(title, synopsis, director, genreID, duration, minimumAge));

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    validators.addAll(ValidationBuilder.of().required(requiredFields).validateUUID(genreID).build());

    return validators;
  }
}
