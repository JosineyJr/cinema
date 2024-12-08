package com.cinema.application.controllers.movies;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.MovieSessionDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.movies.MovieSession;
import com.cinema.domain.usecases.movies.ListMovieSessionsUseCase;

public class ListMovieSessionsController extends Controller<Object> {
  private ListMovieSessionsUseCase listMovieSessionsUseCase;

  public ListMovieSessionsController(ListMovieSessionsUseCase listMovieSessionsUseCase) {
    this.listMovieSessionsUseCase = listMovieSessionsUseCase;
  }

  /**
   * Performs the action of listing movie sessions.
   *
   * @param object The object parameter (not used in this implementation).
   * @return A Response object containing a list of MovieSessionDTO objects.
   * @throws Exception if an error occurs during the execution.
   */
  @Override
  public Response<?> perform(Object object) {
    try {
      List<MovieSession> movieSessions = this.listMovieSessionsUseCase.execute();

      List<MovieSessionDTO> movieSessionsDTO = new ArrayList<MovieSessionDTO>();

      for (MovieSession movieSession : movieSessions) {
        movieSessionsDTO.add(new MovieSessionDTO(movieSession.getID(), movieSession.getMovie().getTitle(),
            movieSession.getCinemaHall().getName(), movieSession.getStartDate().toString()));
      }

      return ResponseFactory.ok(movieSessionsDTO);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Builds and returns a list of validators for the given object.
   *
   * @param object The object for which validators need to be built.
   * @return An ArrayList of IValidator objects representing the validators.
   */
  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }

}
