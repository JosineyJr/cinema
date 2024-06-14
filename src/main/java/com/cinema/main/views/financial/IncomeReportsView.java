package com.cinema.main.views.financial;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.Node;
import javafx.scene.text.Text;

import java.util.List;

import com.cinema.application.helpers.Response;
import com.cinema.domain.entities.financial.DailySalesReport;
import com.cinema.domain.entities.financial.MonthlySalesReport;
import com.cinema.main.factories.financial.GetDailySalesFactory;
import com.cinema.main.factories.financial.GetMonthlySalesFactory;

public class IncomeReportsView {

  @FXML
  private BarChart<String, Number> salesCounterDailySales;

  @FXML
  private BarChart<String, Number> salesCounterMonthlySales;

  @FXML
  public void initialize() {
    Response<?> dailySalesResponse = GetDailySalesFactory.make().handle(null);
    Object data = dailySalesResponse.getData();
    if (data instanceof List) {
      List<DailySalesReport> dailySales = (List<DailySalesReport>) data;
      loadDailySalesData(dailySales);
    }

    Response<?> monthlySalesResponse = GetMonthlySalesFactory.make().handle(null);
    data = monthlySalesResponse.getData();
    if (data instanceof List) {
      List<MonthlySalesReport> monthlySales = (List<MonthlySalesReport>) data;
      loadMonthlySalesData(monthlySales);
    }
  }

  private void loadDailySalesData(List<DailySalesReport> dailySalesReport) {
    XYChart.Series<String, Number> dailySeries = new XYChart.Series<>();
    dailySeries.setName("Vendas Diárias");

    for (DailySalesReport dailySales : dailySalesReport) {
      String counterId = dailySales.getSalesCounter().getID().toString().split("-")[0];
      Number totalPrice = dailySales.getTotalPrice();

      dailySeries.getData().add(new XYChart.Data<>(counterId, totalPrice));
    }

    salesCounterDailySales.getData().add(dailySeries);
    addDataLabelsAndColors(salesCounterDailySales);
  }

  private void loadMonthlySalesData(List<MonthlySalesReport> monthlySalesReport) {
    XYChart.Series<String, Number> monthlySeries = new XYChart.Series<>();
    monthlySeries.setName("Vendas Mensais");

    for (MonthlySalesReport monthlySales : monthlySalesReport) {
      String counterId = monthlySales.getSalesCounter().getID().toString().split("-")[0];
      Number totalPrice = monthlySales.getTotalPrice();

      monthlySeries.getData().add(new XYChart.Data<>(counterId, totalPrice));
    }

    salesCounterMonthlySales.getData().add(monthlySeries);
    addDataLabelsAndColors(salesCounterMonthlySales);
  }

  private void addDataLabelsAndColors(BarChart<String, Number> barChart) {
    Color[] colors = { Color.GREEN, Color.BLUE, Color.ORANGE, Color.PURPLE };
    int colorIndex = 0;

    for (XYChart.Series<String, Number> series : barChart.getData()) {
      for (XYChart.Data<String, Number> data : series.getData()) {
        Node node = data.getNode();
        node.setStyle("-fx-bar-fill: " + toRgbString(colors[colorIndex % colors.length]) + ";");

        Text dataLabel = new Text(data.getYValue().toString());
        dataLabel.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-fill: white;");

        StackPane stackPane = (StackPane) node;
        stackPane.getChildren().add(dataLabel);

        Platform.runLater(() -> {
          dataLabel
              .setTranslateX(dataLabel.getBoundsInParent().getWidth() / 2);
          dataLabel.setTranslateY(-dataLabel.getBoundsInParent().getHeight());
        });

        colorIndex++;
      }
    }
  }

  private String toRgbString(Color color) {
    return "rgb(" + (int) (color.getRed() * 255) + "," + (int) (color.getGreen() * 255) + ","
        + (int) (color.getBlue() * 255) + ")";
  }
}