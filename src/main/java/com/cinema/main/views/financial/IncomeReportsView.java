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
import com.cinema.main.factories.financial.GetDailySalesFactory;

public class IncomeReportsView {

  @FXML
  private BarChart<String, Number> salesCounterDailySales;

  @FXML
  private BarChart<String, Number> salesCounterMonthlySales;

  @FXML
  public void initialize() {
    Response<?> response = GetDailySalesFactory.make().handle(null);
    Object data = response.getData();
    System.out.println(data);
    if (data instanceof List) {
      List<DailySalesReport> dailySales = (List<DailySalesReport>) data;
      loadDailySalesData(dailySales);
    }

    XYChart.Series<String, Number> monthlySeries = new XYChart.Series<>();
    monthlySeries.setName("Vendas Mensais");
    monthlySeries.getData().add(new XYChart.Data<>("Counter 1", 4));
    monthlySeries.getData().add(new XYChart.Data<>("Counter 2", 2));
    monthlySeries.getData().add(new XYChart.Data<>("Counter 3", 3));
    monthlySeries.getData().add(new XYChart.Data<>("Counter 4", 10));
    monthlySeries.getData().add(new XYChart.Data<>("Counter 5", 6));
    salesCounterMonthlySales.getData().add(monthlySeries);
    addDataLabelsAndColors(salesCounterMonthlySales);
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

  private void addDataLabelsAndColors(BarChart<String, Number> barChart) {
    Color[] colors = { Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.PURPLE };
    int colorIndex = 0;

    for (XYChart.Series<String, Number> series : barChart.getData()) {
      for (XYChart.Data<String, Number> data : series.getData()) {
        Node node = data.getNode();
        node.setStyle("-fx-bar-fill: " + toRgbString(colors[colorIndex % colors.length]) + ";");

        Text dataLabel = new Text(data.getYValue().toString());
        dataLabel.setStyle("-fx-font-size: 10px; -fx-font-weight: bold;");

        StackPane stackPane = (StackPane) node;
        stackPane.getChildren().add(dataLabel);

        Platform.runLater(() -> {
          dataLabel
              .setTranslateX(dataLabel.getBoundsInParent().getWidth() / 2);
          dataLabel.setTranslateY(node.getBoundsInParent().getHeight() / 2 - 7);
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