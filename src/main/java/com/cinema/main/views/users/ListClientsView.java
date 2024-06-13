package com.cinema.main.views.users;

import java.util.List;

import com.cinema.application.dtos.users.ClientDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.users.ListClientsFactory;
import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.ActionCellFactory;
import com.cinema.main.views.helpers.CellValueFactoryUtil;
import com.cinema.main.views.helpers.ChangeWindow;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ListClientsView {

  @FXML
  private TableColumn<ClientDTO, String> CPF;

  @FXML
  private TableColumn<ClientDTO, Void> action;

  @FXML
  private TableColumn<ClientDTO, String> name;

  @FXML
  private TableView<ClientDTO> clientsTableView;

  @FXML
  public void initialize() {
    Response<?> response = ListClientsFactory.make().handle(null);

    Object data = response.getData();

    if (data instanceof List) {
      ObservableList<ClientDTO> clients = FXCollections.observableArrayList();

      for (Object client : (List<?>) data) {
        ClientDTO clientDTO = (ClientDTO) client;

        clients.add(clientDTO);
      }

      this.clientsTableView.setItems(clients);
    }

    this.name.setCellValueFactory(CellValueFactoryUtil
        .createCellValueFactory(client -> client.getFirstName() + " " + client.getLastName()));
    this.name.setStyle("-fx-alignment: CENTER;");

    this.CPF.setCellValueFactory(CellValueFactoryUtil.createCellValueFactory(ClientDTO::getCPF));
    this.CPF.setStyle("-fx-alignment: CENTER;");

    this.action.setCellFactory(new ActionCellFactory<>(this::editClient, this::deleteClient));
  }

  private void deleteClient(ClientDTO client) {
    System.out.println("Deleting client: " + client.getFirstName() + " " + client.getLastName());
  }

  private void editClient(ClientDTO clientDTO) throws Exception {
    ClientModel.getInstance().setClient(clientDTO);

    Stage primaryStage = StageManager.getPrimaryStage();

    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/users/editClient.fxml");
  }
}
