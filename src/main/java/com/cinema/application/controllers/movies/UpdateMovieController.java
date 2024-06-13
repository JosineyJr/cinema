package com.cinema.application.controllers.movies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.UpdateMovieDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.errors.movies.GenreNotFoundError;
import com.cinema.domain.errors.movies.MovieNotFoundError;
import com.cinema.domain.usecases.movies.UpdateMovieUseCase;

public class UpdateMovieController extends Controller<UpdateMovieDTO> {
  private UpdateMovieUseCase updateMovieUseCase;

  public UpdateMovieController(UpdateMovieUseCase updateMovieUseCase) {
    this.updateMovieUseCase = updateMovieUseCase;
  }

  public Response<?> perform(UpdateMovieDTO updateMovieDTO) {
    try {
      UUID genreUUID = UUID.fromString(updateMovieDTO.getGenreID());

      this.updateMovieUseCase.execute(
          UUID.fromString(updateMovieDTO.getID()),
          updateMovieDTO.getTitle(),
          updateMovieDTO.getSynopsis(),
          updateMovieDTO.getDirector(),
          genreUUID,
          updateMovieDTO.getDuration(),
          updateMovieDTO.getMinimumAge());

      return ResponseFactory.noContent();
    } catch (MovieNotFoundError e) {

      return ResponseFactory.badRequest(e);
    } catch (GenreNotFoundError e) {

      return ResponseFactory.badRequest(e);
    } catch (Exception e) {

      return ResponseFactory.serverError(e);
    }
  }

  public ArrayList<IValidator> buildValidators(UpdateMovieDTO object) {
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
