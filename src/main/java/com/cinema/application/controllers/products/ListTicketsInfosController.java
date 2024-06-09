package com.cinema.application.controllers.products;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.movies.MovieSessionDTO;
import com.cinema.application.dtos.products.TicketInfosDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.products.TicketInfos;
import com.cinema.domain.usecases.products.ListTicketsInfosUseCase;

public class ListTicketsInfosController extends Controller<Object> {
  private ListTicketsInfosUseCase listTicketsInfosUseCase;

  public ListTicketsInfosController(ListTicketsInfosUseCase listTicketsInfosUseCase) {
    this.listTicketsInfosUseCase = listTicketsInfosUseCase;
  }

  @Override
  public Response<?> perform(Object object) {
    try {
      List<TicketInfos> tickets = this.listTicketsInfosUseCase.execute();

      List<TicketInfosDTO> ticketsInfosDTO = new ArrayList<TicketInfosDTO>();

      for (TicketInfos ticket : tickets) {
        MovieSessionDTO movieSessionDTO = new MovieSessionDTO(ticket.getMovieSession().getID(),
            ticket.getMovieSession().getMovie().getTitle(), ticket.getMovieSession().getCinemaHall().getName(),
            ticket.getMovieSession().getStartDate().toString());

        ticketsInfosDTO.add(new TicketInfosDTO(ticket.getID(), ticket.getPrice(), movieSessionDTO));
      }

      return ResponseFactory.ok(ticketsInfosDTO);
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }

}
