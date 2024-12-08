package com.cinema.application.controllers.movies;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.MovieDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.movies.Movie;
import com.cinema.domain.usecases.movies.ListMoviesUseCase;

public class ListMoviesController extends Controller<Object> {
  private ListMoviesUseCase listMoviesUseCase;

  public ListMoviesController(ListMoviesUseCase listMoviesUseCase) {
    this.listMoviesUseCase = listMoviesUseCase;
  }

  /**
   * Performs the necessary operations to retrieve a list of movies and convert them into a list of MovieDTO objects.
   * 
   * @param object The input object (not used in this implementation).
   * @return A Response object containing a list of MovieDTO objects.
   * @throws Exception if an error occurs during the execution.
   */
  @Override
  public Response<?> perform(Object object) {
    try {
      List<Movie> movies = this.listMoviesUseCase.execute();

      List<MovieDTO> moviesDTO = new ArrayList<MovieDTO>();

      for (Movie movie : movies) {
        moviesDTO.add(new MovieDTO(movie.getID(), movie.getTitle(), movie.getSynopsis(), movie.getDirector(),
            movie.getGenre(), movie.getDuration(), movie.getMinimumAge()));
      }

      return ResponseFactory.ok(moviesDTO);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Builds and returns a list of validators for the given object.
   *
   * @param object The object for which validators need to be built.
   * @return An ArrayList of IValidator objects representing the validators for the given object.
   */
  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }

}
