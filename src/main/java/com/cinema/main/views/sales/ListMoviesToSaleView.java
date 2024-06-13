package com.cinema.main.views.sales;

import java.util.List;

import com.cinema.application.dtos.products.TicketDTO;
import com.cinema.application.dtos.sales.AddTicketToCartDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.products.ListTicketsFactory;
import com.cinema.main.factories.sales.AddTicketToCartFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.ButtonTableCell;
import com.cinema.main.views.helpers.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class represents the view for listing ticket information.
 * It displays a table of available sessions with cinema hall, movie, start
 * date, and ticket price.
 * It also provides a button to add a ticket to the shopping cart.
 */
public class ListMoviesToSaleView {

  @FXML
  private TableColumn<TicketDTO, Void> action;

  @FXML
  private TableView<TicketDTO> availableSessions;

  @FXML
  private TableColumn<TicketDTO, String> cinemaHall;

  @FXML
  private TableColumn<TicketDTO, String> movie;

  @FXML
  private TableColumn<TicketDTO, String> startDate;

  @FXML
  private TableColumn<TicketDTO, String> ticketPrice;

  /**
   * Initializes the view by fetching the ticket information and setting up the
   * table.
   */
  @FXML
  public void initialize() {
    Response<?> response = ListTicketsFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      ObservableList<TicketDTO> tickets = FXCollections.observableArrayList();

      for (Object ticket : (List<?>) data) {
        if (ticket instanceof TicketDTO) {
          TicketDTO ticketDTO = (TicketDTO) ticket;

          LocalDateTime movieSessionStartTime = LocalDateTime.parse(
              ticketDTO.getMovieSession().getStartTime(),
              DateTimeFormatter.ISO_LOCAL_DATE_TIME);

          LocalDateTime currentDateTime = LocalDateTime.now();

          if (movieSessionStartTime.toLocalDate().isEqual(currentDateTime.toLocalDate())
              && movieSessionStartTime
                  .isAfter(currentDateTime)) {
            tickets.add(ticketDTO);
          }
        }
      }

      availableSessions.setItems(tickets);
    }

    cinemaHall.setCellValueFactory(new PropertyValueFactory<>("cinemaHall"));
    cinemaHall.setStyle("-fx-alignment: CENTER;");

    startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    startDate.setStyle("-fx-alignment: CENTER;");

    movie.setCellValueFactory(new PropertyValueFactory<>("movie"));
    movie.setStyle("-fx-alignment: CENTER;");

    ticketPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    ticketPrice.setStyle("-fx-alignment: CENTER;");

    action.setCellFactory(column -> new ButtonTableCell<>("Add carrinho", this::addToCart));
  }

  /**
   * Adds the selected ticket to the shopping cart.
   * 
   * @param ticket The ticket to be added to the shopping cart.
   */
  private void addToCart(TicketDTO ticket) {
    System.out.println("Adicionando ao carrinho: " + ticket);

    System.out.println("CPF: " + Session.getCPF());

    AddTicketToCartDTO addTicketToCartDTO = new AddTicketToCartDTO(ticket.getID().toString(), Session.getCPF());

    Response<?> response = AddTicketToCartFactory.make().handle(addTicketToCartDTO);

    if (response.getStatusCode() == 204) {
      new AlertSuccess("Ingresso adicionado ao carrinho com sucesso!");
    } else {
      new AlertError(response.getData().toString());
    }
  }

}
