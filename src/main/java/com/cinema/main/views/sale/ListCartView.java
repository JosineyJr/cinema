package com.cinema.main.views.sale;

import java.util.UUID;

import com.cinema.application.dtos.sales.TicketsCartDTO;
import com.cinema.application.dtos.sales.ListCartDTO;
import com.cinema.application.dtos.sales.ProductsCartDTO;
import com.cinema.application.helpers.Response;
import com.cinema.domain.entities.movies.MovieSession;
import com.cinema.domain.entities.products.Product;
import com.cinema.domain.entities.products.ProductInfos;
import com.cinema.domain.entities.products.Ticket;
import com.cinema.domain.entities.products.TicketInfos;
import com.cinema.domain.entities.sale.Cart;
import com.cinema.main.factories.sales.ListPersonCartFactory;
import com.cinema.main.views.helpers.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ListCartView {
  @FXML
  private TableColumn<TicketsCartDTO, Void> ticketsAction;

  @FXML
  private TableColumn<TicketsCartDTO, String> movie;

  @FXML
  private TableColumn<TicketsCartDTO, String> startDate;

  @FXML
  private TableColumn<TicketsCartDTO, String> cinemaHall;

  @FXML
  private TableColumn<TicketsCartDTO, Double> ticketsPrice;

  @FXML
  private TableView<TicketsCartDTO> ticketsTable;

  @FXML
  private TableColumn<ProductsCartDTO, String> name;

  @FXML
  private TableColumn<ProductsCartDTO, Double> productsPrice;

  @FXML
  private TableColumn<ProductsCartDTO, Void> productsAction;

  @FXML
  private TableView<ProductsCartDTO> productsTable;

  @FXML
  void initialize() {
    UUID personID = UUID.fromString(Session.getCPF());
    System.out.println(personID);
    ListCartDTO listCartDTO = new ListCartDTO(personID);
    Response<?> response = ListPersonCartFactory.make().handle(listCartDTO);

    Object data = response.getData();

    if (data != null) {
      Cart cart = (Cart) data;

      ObservableList<TicketsCartDTO> ticketItems = FXCollections.observableArrayList();
      ObservableList<ProductsCartDTO> productsItems = FXCollections.observableArrayList();

      for (Ticket ticket : cart.getTickets()) {
        TicketInfos ticketInfos = ticket.getTicketInfos();
        MovieSession movieSession = ticketInfos.getMovieSession();

        String movie = movieSession.getMovie().getTitle();
        String startDate = movieSession.getStartDate().toString();
        String cinemaHall = movieSession.getCinemaHall().getName();
        double price = ticketInfos.getPrice();

        TicketsCartDTO item = new TicketsCartDTO(cart.getID(), movie, cinemaHall, price, startDate, personID);

        ticketItems.add(item);
      }
      ticketsTable.setItems(ticketItems);

      for(Product product : cart.getProducts()) {
        ProductInfos productInfos = product.getProductInfos();

        String name = productInfos.getName();
        double price = productInfos.getPrice();

        ProductsCartDTO item = new ProductsCartDTO(cart.getID(), name, price, personID);

        productsItems.add(item);
      }
      productsTable.setItems(productsItems);
    }

    movie.setCellValueFactory(new PropertyValueFactory<TicketsCartDTO, String>("movie"));
    movie.setStyle("-fx-alignment: CENTER;");

    ticketsPrice.setCellValueFactory(new PropertyValueFactory<TicketsCartDTO, Double>("price"));
    ticketsPrice.setStyle("-fx-alignment: CENTER;");

    cinemaHall.setCellValueFactory(new PropertyValueFactory<TicketsCartDTO, String>("cinemaHall"));
    cinemaHall.setStyle("-fx-alignment: CENTER;");

    startDate.setCellValueFactory(new PropertyValueFactory<TicketsCartDTO, String>("startDate"));
    startDate.setStyle("-fx-alignment: CENTER;");

    name.setCellValueFactory(new PropertyValueFactory<ProductsCartDTO, String>("name"));
    name.setStyle("-fx-alignment: CENTER;");

    productsPrice.setCellValueFactory(new PropertyValueFactory<ProductsCartDTO, Double>("price"));
    productsPrice.setStyle("-fx-alignment: CENTER;");
  }
}
