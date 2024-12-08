package com.cinema.main.views.users;

import com.cinema.application.dtos.users.ClientDTO;
import com.cinema.application.dtos.users.UpdateClientDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.users.UpdateClientFactory;
import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.ChangeWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditClientView {
  @FXML
  private TextField CPF;

  @FXML
  private TextField firstName;

  @FXML
  private TextField lastName;

  private ClientDTO client;

  @FXML
  void initialize() {
    client = ClientModel.getInstance().getClient();

    CPF.setText(client.getCPF());
    firstName.setText(client.getFirstName());
    lastName.setText(client.getLastName());
  }

  @FXML
  void editClient(ActionEvent event) throws Exception {
    UpdateClientDTO updateClientDTO = new UpdateClientDTO(client.getID(), firstName.getText(), lastName.getText(),
        CPF.getText());

    Response<?> response = UpdateClientFactory.make().handle(updateClientDTO);

    if (response.getStatusCode() == 204) {
      new AlertSuccess("Cliente editado com sucesso!");

      Stage primaryStage = StageManager.getPrimaryStage();

      ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/users/listClients.fxml");
    } else {
      new AlertError(response.getData().toString());
    }
  }
}
