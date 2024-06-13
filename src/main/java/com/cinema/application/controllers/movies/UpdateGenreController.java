package com.cinema.application.controllers.movies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.UpdateGenreDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.usecases.movies.UpdateGenreUseCase;

public class UpdateGenreController extends Controller<UpdateGenreDTO> {
  private UpdateGenreUseCase updateGenreUseCase;

  public UpdateGenreController(UpdateGenreUseCase updateGenreUseCase) {
    this.updateGenreUseCase = updateGenreUseCase;
  }

  public Response<?> perform(UpdateGenreDTO updateGenreDTO) {
    try {
      UUID genreID = UUID.fromString(updateGenreDTO.getID());
      Genre genre = new Genre(genreID, updateGenreDTO.getName());

      updateGenreUseCase.execute(genre);
      return ResponseFactory.noContent();
    } catch (Exception e) {
      return ResponseFactory.serverError(e);
    }
  }

  /**
   * Builds and returns a list of validators for updating a genre.
   *
   * @param object The UpdateGenreDTO object containing the genre information to be validated.
   * @return An ArrayList of IValidator objects representing the validators for the genre update.
   */
  @Override
  public ArrayList<IValidator> buildValidators(UpdateGenreDTO object) {

    Field genreID = new Field(object.getID(), "ID");
    Field genreName = new Field(object.getName(), "Nome");

    ArrayList<Field> requiredFields = new ArrayList<>(
        Arrays.asList(genreName, genreID));

    ArrayList<IValidator> validators = new ArrayList<>();

    validators.addAll(ValidationBuilder.of().required(requiredFields).validateUUID(genreID).build());

    return validators;
  }
}
