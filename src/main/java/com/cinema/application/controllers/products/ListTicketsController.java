package com.cinema.application.controllers.products;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.MovieSessionDTO;
import com.cinema.application.dtos.products.TicketDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.products.Ticket;
import com.cinema.domain.usecases.products.ListTicketsUseCase;

public class ListTicketsController extends Controller<Object> {
  private ListTicketsUseCase listTicketsUseCase;

  public ListTicketsController(ListTicketsUseCase listTicketsUseCase) {
    this.listTicketsUseCase = listTicketsUseCase;
  }

  /**
   * Performs the necessary operations to retrieve a list of ticket information.
   *
   * @param object The input object (not used in this implementation).
   * @return A Response object containing the list of TicketDTO.
   * @throws Exception if an error occurs during the execution.
   */
  @Override
  public Response<?> perform(Object object) {
    try {
      List<Ticket> tickets = this.listTicketsUseCase.execute();

      List<TicketDTO> ticketsDTO = new ArrayList<TicketDTO>();

      for (Ticket ticket : tickets) {
        MovieSessionDTO movieSessionDTO = new MovieSessionDTO(ticket.getMovieSession().getID(),
            ticket.getMovieSession().getMovie().getTitle(), ticket.getMovieSession().getCinemaHall().getName(),
            ticket.getMovieSession().getStartDate().toString());

        ticketsDTO.add(new TicketDTO(ticket.getID(), ticket.getPrice(), movieSessionDTO));
      }

      return ResponseFactory.ok(ticketsDTO);
    } catch (Exception e) {
      throw e;
    }
  }

  /**
   * Builds and returns a list of validators for the given object.
   *
   * @param object The object for which validators need to be built.
   * @return An ArrayList of IValidator objects.
   */
  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }

}
