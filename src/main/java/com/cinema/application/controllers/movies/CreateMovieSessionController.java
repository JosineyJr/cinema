package com.cinema.application.controllers.movies;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.CreateMovieSessionDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.errors.movies.CinemaHallNotFoundError;
import com.cinema.domain.errors.movies.MovieNotFoundError;
import com.cinema.domain.errors.movies.MovieSessionAlreadyScreeningInCinemaHallError;
import com.cinema.domain.usecases.movies.CreateMovieSessionUseCase;

public class CreateMovieSessionController extends Controller {
  private CreateMovieSessionUseCase createMovieSessionUseCase;

  public CreateMovieSessionController(CreateMovieSessionUseCase createMovieSessionUseCase) {
    this.createMovieSessionUseCase = createMovieSessionUseCase;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Response perform(Object object) {
    try {
      CreateMovieSessionDTO createMovieSessionDTO = (CreateMovieSessionDTO) object;

      UUID movieID = UUID.fromString(createMovieSessionDTO.getMovieID());
      UUID cinemaHallID = UUID.fromString(createMovieSessionDTO.getCinemaHallID());

      LocalDateTime startDate = LocalDateTime.parse(createMovieSessionDTO.getStartDate());

      this.createMovieSessionUseCase.execute(movieID, cinemaHallID, startDate);

      return ResponseFactory.noContent();
    } catch (MovieNotFoundError | CinemaHallNotFoundError
        | MovieSessionAlreadyScreeningInCinemaHallError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    CreateMovieSessionDTO createMovieSessionDTO = (CreateMovieSessionDTO) object;

    Field movieID = new Field(createMovieSessionDTO.getMovieID(), "Filme");
    Field cinemaHallID = new Field(createMovieSessionDTO.getCinemaHallID(), "Sala de Cinema");
    Field startDate = new Field(createMovieSessionDTO.getStartDate(), "Hora de Início");

    ArrayList<Field> requiredFields = new ArrayList<>(
        Arrays.asList(movieID, cinemaHallID, startDate));

    ArrayList<IValidator> validators = new ArrayList<>();

    validators.addAll(
        ValidationBuilder.of().required(requiredFields).validateUUID(movieID).validateUUID(cinemaHallID)
            .validateLocalDateTime(startDate).build());

    return validators;
  }
}
