package com.cinema.main.views.products;

import java.util.List;

import com.cinema.application.dtos.products.TicketDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.products.ListTicketsFactory;
import com.cinema.main.views.helpers.ButtonTableCell;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ListTicketsView {

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

    private void addToCart(TicketDTO ticket) {
        System.out.println("Adicionando ao carrinho: " + ticket);
    }

}
