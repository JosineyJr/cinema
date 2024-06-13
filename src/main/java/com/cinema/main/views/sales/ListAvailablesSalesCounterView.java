package com.cinema.main.views.sales;

import java.util.Optional;

import com.cinema.application.dtos.sales.ChangeAvailableSalesCounterDTO;
import com.cinema.application.dtos.sales.SalesCounterDTO;
import com.cinema.application.helpers.Response;
import com.cinema.domain.entities.sale.SalesCounter;
import com.cinema.main.factories.sales.ChangeAvailableSalesCounterFactory;
import com.cinema.main.factories.sales.CreateOrListSalesCounterFactory;
import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.ButtonTableCell;
import com.cinema.main.views.helpers.ChangeWindow;
import com.cinema.main.views.helpers.Session;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ListAvailablesSalesCounterView {
    private static final SalesCounter[] salesCounters = new SalesCounter[5];

    @FXML
    private TableColumn<SalesCounterDTO, Void> action;

    @FXML
    private TableView<SalesCounterDTO> availablesSalesCounter;

    @FXML
    private TableColumn<SalesCounterDTO, String> id;

    @FXML
    private TableColumn<SalesCounterDTO, String> type;

    @FXML
    public void initialize() {
        Response<?> response = CreateOrListSalesCounterFactory.make().handle(null);

        Object data = response.getData();

        if (data != null) {
            ObservableList<SalesCounterDTO> salesCounterItens = FXCollections.observableArrayList();

            SalesCounterDTO[] salesCounterDTOs = (SalesCounterDTO[]) data;

            for (int i = 0; i < salesCounterDTOs.length; i++) {
                salesCounters[i] = new SalesCounter(salesCounterDTOs[i].getCompleteID(), salesCounterDTOs[i].getType(),
                        salesCounterDTOs[i].getIsAvailable());

                salesCounterItens.add(salesCounterDTOs[i]);
            }

            availablesSalesCounter.setItems(salesCounterItens);
        }

        id.setCellValueFactory(new PropertyValueFactory<SalesCounterDTO, String>("ID"));
        id.setStyle("-fx-alignment: CENTER;");

        type.setCellValueFactory(new PropertyValueFactory<SalesCounterDTO, String>("type"));
        type.setStyle("-fx-alignment: CENTER;");

        action.setCellFactory(column -> new ButtonTableCell<>("Selecionar", this::selectSalesCounter));

    }

    private void selectSalesCounter(SalesCounterDTO salesCounterDTO) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de Seleção");
        alert.setHeaderText("Voce deseja selecionar o caixa " + salesCounterDTO.getID() + "?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            Session.setSalesCounterId(salesCounterDTO.getCompleteID());

            ChangeAvailableSalesCounterDTO changeAvailableSalesCounterDTO = new ChangeAvailableSalesCounterDTO(
                    salesCounterDTO.getCompleteID(), false);

            Response<?> response = ChangeAvailableSalesCounterFactory.make().handle(changeAvailableSalesCounterDTO);

            if (response.getStatusCode() == 204) {
                Stage primaryStage = StageManager.getPrimaryStage();

                try {
                    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/auth/login.fxml");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                new AlertError("Erro ao selecionar o caixa.");
            }

        }

    }
}
