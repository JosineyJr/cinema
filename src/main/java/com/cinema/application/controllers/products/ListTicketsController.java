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

  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }

}
