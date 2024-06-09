package com.cinema.application.controllers.movies;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.CinemaHallDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.movies.CinemaHall;
import com.cinema.domain.usecases.movies.ListCinemaHallsUseCase;

public class ListCinemaHallsController extends Controller<Object> {
  private ListCinemaHallsUseCase listCinemaHallsUseCase;

  public ListCinemaHallsController(ListCinemaHallsUseCase listCinemaHallsUseCase) {
    this.listCinemaHallsUseCase = listCinemaHallsUseCase;
  }

  /**
   * Executes the logic to retrieve a list of cinema halls and convert them into a list of CinemaHallDTO objects.
   * 
   * @param object The input object (not used in this implementation).
   * @return A Response object containing a list of CinemaHallDTO objects.
   * @throws Exception if an error occurs during the execution.
   */
  @Override
  public Response<?> perform(Object object) {
    try {
      List<CinemaHall> cinemaHalls = this.listCinemaHallsUseCase.execute();

      List<CinemaHallDTO> cinemaHallsDTO = new ArrayList<CinemaHallDTO>();

      for (CinemaHall cinemaHall : cinemaHalls) {
        cinemaHallsDTO.add(new CinemaHallDTO(cinemaHall.getID(), cinemaHall.getName(), cinemaHall.getCapacity()));
      }

      return ResponseFactory.ok(cinemaHallsDTO);
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
