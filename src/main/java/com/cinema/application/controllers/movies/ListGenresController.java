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

  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }
}
