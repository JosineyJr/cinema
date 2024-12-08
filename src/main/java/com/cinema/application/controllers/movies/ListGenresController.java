package com.cinema.application.controllers.movies;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.GenreDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.movies.Genre;
import com.cinema.domain.usecases.movies.ListGenresUseCase;

public class ListGenresController extends Controller<Object> {
  private ListGenresUseCase listGenresUseCase;

  public ListGenresController(ListGenresUseCase listGenresUseCase) {
    this.listGenresUseCase = listGenresUseCase;
  }

  /**
   * Performs the operation to list all genres.
   *
   * @param object The input object (not used in this implementation).
   * @return A Response object containing a list of GenreDTO objects.
   * @throws Exception if an error occurs during the operation.
   */
  @Override
  public Response<List<GenreDTO>> perform(Object object) {
    try {
      List<Genre> genres = this.listGenresUseCase.execute();

      List<GenreDTO> genresDTO = new ArrayList<GenreDTO>();

      for (Genre genre : genres) {
        genresDTO.add(new GenreDTO(genre.getID(), genre.getName()));
      }

      return ResponseFactory.ok(genresDTO);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Builds and returns a list of validators for the specified object.
   *
   * @param object The object for which validators need to be built.
   * @return An ArrayList of IValidator objects representing the validators for the specified object.
   */
  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }
}
