package com.cinema.main.views.sales;

import java.util.UUID;
import java.util.List;

import com.cinema.application.dtos.sales.TicketsCartDTO;
import com.cinema.application.dtos.sales.CompleteSaleDTO;
import com.cinema.application.dtos.sales.ListCartDTO;
import com.cinema.application.dtos.sales.ProductsCartDTO;
import com.cinema.application.dtos.sales.RemoveProductFromCartDTO;
import com.cinema.application.dtos.sales.RemoveTicketCartFromCartDTO;
import com.cinema.application.helpers.Response;
import com.cinema.domain.entities.movies.MovieSession;
import com.cinema.domain.entities.products.Product;
import com.cinema.domain.entities.products.Ticket;
import com.cinema.domain.entities.sale.Cart;
import com.cinema.domain.entities.sale.ProductCart;
import com.cinema.domain.entities.sale.TicketCart;
import com.cinema.main.factories.sales.CompleteSaleFactory;
import com.cinema.main.factories.sales.ListPersonCartFactory;
import com.cinema.main.factories.sales.RemoveProductFromCartFactory;
import com.cinema.main.factories.sales.RemoveTicketFromCartFactory;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.ButtonTableCell;
import com.cinema.main.views.helpers.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
  private Label totalCartValue;

  @FXML
  void initialize() {
    UUID personID = UUID.fromString(Session.getCPF());
    ListCartDTO listCartDTO = new ListCartDTO(personID);
    Response<?> response = ListPersonCartFactory.make().handle(listCartDTO);

    Object data = response.getData();

    if (data != null) {
      Cart cart = (Cart) data;

      ObservableList<TicketsCartDTO> ticketItems = FXCollections.observableArrayList();
      ObservableList<ProductsCartDTO> productsItems = FXCollections.observableArrayList();

      for (TicketCart ticketCart : cart.getTickets()) {
        Ticket ticket = ticketCart.getTicket();
        MovieSession movieSession = ticket.getMovieSession();

        String movie = movieSession.getMovie().getTitle();
        String startDate = movieSession.getStartDate().toString();
        String cinemaHall = movieSession.getCinemaHall().getName();
        double price = ticket.getPrice();

        TicketsCartDTO ticketCartItem = new TicketsCartDTO(ticketCart.getID(), movie, cinemaHall, price, startDate,
            personID);

        ticketItems.add(ticketCartItem);
      }
      ticketsTable.setItems(ticketItems);

      for (ProductCart productCart : cart.getProducts()) {
        Product product = productCart.getProduct();

        String name = product.getName();
        double price = product.getPrice();

        ProductsCartDTO item = new ProductsCartDTO(productCart.getID(), name, price, personID);

        productsItems.add(item);
      }
      productsTable.setItems(productsItems);

      calculateTotalCartValue();
    }

    movie.setCellValueFactory(new PropertyValueFactory<TicketsCartDTO, String>("movie"));
    movie.setStyle("-fx-alignment: CENTER;");

    ticketsPrice.setCellValueFactory(new PropertyValueFactory<TicketsCartDTO, Double>("price"));
    ticketsPrice.setStyle("-fx-alignment: CENTER;");

    cinemaHall.setCellValueFactory(new PropertyValueFactory<TicketsCartDTO, String>("cinemaHall"));
    cinemaHall.setStyle("-fx-alignment: CENTER;");

    startDate.setCellValueFactory(new PropertyValueFactory<TicketsCartDTO, String>("startDate"));
    startDate.setStyle("-fx-alignment: CENTER;");

    ticketsAction.setCellFactory(column -> new ButtonTableCell<>("Remover", this::removeTicket));

    name.setCellValueFactory(new PropertyValueFactory<ProductsCartDTO, String>("name"));
    name.setStyle("-fx-alignment: CENTER;");

    productsPrice.setCellValueFactory(new PropertyValueFactory<ProductsCartDTO, Double>("price"));
    productsPrice.setStyle("-fx-alignment: CENTER;");

    productsAction.setCellFactory(column -> new ButtonTableCell<>("Remover", this::removeProduct));
  }

  private void removeTicket(TicketsCartDTO ticketCart) {
    showConfirmationDialog(ticketCart);
  }

  private void removeProduct(ProductsCartDTO productCart) {
    showConfirmationDialog(productCart);
  }

  private void showConfirmationDialog(ProductsCartDTO productCart) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    alert.setTitle("Confirmação de Exclusão");
    alert.setHeaderText("Deseja realmente excluir o produto" + productCart.getName() + "?");

    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) {
        RemoveProductFromCartDTO removeProductFromCartDTO = new RemoveProductFromCartDTO(productCart.getID());
        Response<?> responseRemove = RemoveProductFromCartFactory.make().handle(removeProductFromCartDTO);

        if (responseRemove.getStatusCode() == 204) {
          new AlertSuccess("Produto deletado com sucesso!");
          productsTable.getItems().remove(productCart);
          calculateTotalCartValue();
        } else {
          new AlertError(responseRemove.getData().toString());
        }
      }
    });
  }

  private void showConfirmationDialog(TicketsCartDTO ticket) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    alert.setTitle("Confirmação de Exclusão");
    alert.setHeaderText("Deseja realmente excluir o ticket" + ticket.getMovie() + "?");

    alert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) {
        RemoveTicketCartFromCartDTO removeTicketFromCartDTO = new RemoveTicketCartFromCartDTO(ticket.getID());

        Response<?> responseRemove = RemoveTicketFromCartFactory.make().handle(removeTicketFromCartDTO);

        if (responseRemove.getStatusCode() == 204) {
          new AlertSuccess("Ticket deletado com sucesso!");
          ticketsTable.getItems().remove(ticket);
          calculateTotalCartValue();
        } else {
          new AlertError(responseRemove.getData().toString());
        }
      }
    });
  }

  private void calculateTotalCartValue() {
    double total = 0;

    for (TicketsCartDTO ticket : ticketsTable.getItems()) {
      total += ticket.getPrice();
    }

    for (ProductsCartDTO productCart : productsTable.getItems()) {
      total += productCart.getPrice();
    }

    totalCartValue.setText("R$" + total);
  }

  @FXML
  void saleCheckOut(ActionEvent event) {
    List<ProductsCartDTO> productsCart = productsTable.getItems();
    List<TicketsCartDTO> ticketsCart = ticketsTable.getItems();

    UUID personID = UUID.fromString(Session.getCPF());

    CompleteSaleDTO completeSaleDTO = new CompleteSaleDTO(productsCart, ticketsCart, personID);

    Response<?> response = CompleteSaleFactory.make().handle(completeSaleDTO);

    if (response.getStatusCode() == 204) {
      new AlertSuccess("Venda realizada com sucesso!");

      productsTable.getItems().clear();
      ticketsTable.getItems().clear();
      totalCartValue.setText("R$0.0");

    } else {
      new AlertError(response.getData().toString());

    }
  }
}
