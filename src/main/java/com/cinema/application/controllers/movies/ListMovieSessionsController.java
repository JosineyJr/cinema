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

  @Override
  public Response<?> perform(Object object) {
    try {
      List<MovieSession> movieSessions = this.listMovieSessionsUseCase.execute();

      List<MovieSessionDTO> movieSessionsDTO = new ArrayList<MovieSessionDTO>();

      for (MovieSession movieSession : movieSessions) {
        movieSessionsDTO.add(new MovieSessionDTO(movieSession.getID(), movieSession.getMovie().getTitle(),
            movieSession.getCinemaHall().getName(), movieSession.getStartTime().toString()));
      }

      return ResponseFactory.ok(movieSessionsDTO);
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }

}
