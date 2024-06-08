package com.cinema.application.controllers.movies;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

public class CreateMovieSessionController extends Controller<CreateMovieSessionDTO> {
  private CreateMovieSessionUseCase createMovieSessionUseCase;

  public CreateMovieSessionController(CreateMovieSessionUseCase createMovieSessionUseCase) {
    this.createMovieSessionUseCase = createMovieSessionUseCase;
  }

  @Override
  public Response<?> perform(CreateMovieSessionDTO object) {
    try {

      UUID movieID = UUID.fromString(object.getMovieID());
      UUID cinemaHallID = UUID.fromString(object.getCinemaHallID());
      double ticketPrice = object.getTicketPrice();

      LocalDateTime formatDate = LocalDateTime.parse(object.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);

      this.createMovieSessionUseCase.execute(movieID, cinemaHallID, formatDate, ticketPrice);

      return ResponseFactory.noContent();
    } catch (MovieNotFoundError | CinemaHallNotFoundError
        | MovieSessionAlreadyScreeningInCinemaHallError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(CreateMovieSessionDTO object) {

    Field movieID = new Field(object.getMovieID(), "Filme");
    Field cinemaHallID = new Field(object.getCinemaHallID(), "Sala de Cinema");
    Field startDate = new Field(object.getStartDate(), "Data de Início");
    Field ticketPrice = new Field(String.valueOf(object.getTicketPrice()), "Preço do Ingresso");

    ArrayList<Field> requiredFields = new ArrayList<>(
        Arrays.asList(movieID, cinemaHallID, startDate, ticketPrice));

    ArrayList<IValidator> validators = new ArrayList<>();

    validators.addAll(
        ValidationBuilder.of().required(requiredFields).validateUUID(movieID).validateUUID(cinemaHallID)
            .validateLocalDateTime(startDate).minDoubleValue(ticketPrice, 0.0).build());

    return validators;
  }
}
