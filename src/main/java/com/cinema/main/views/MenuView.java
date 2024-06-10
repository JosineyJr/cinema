package com.cinema.main.views;

import com.cinema.main.views.helpers.ChangeWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * The MenuView class represents the view for the main menu of the cinema application.
 * It contains methods for handling user actions related to different menu options.
 */
public class MenuView {

  /**
   * Handles the action when the user selects the "List Genres" option from the menu.
   * It changes the scene to the listGenres.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void listGenres(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/listGenres.fxml");
  }

  /**
   * Handles the action when the user selects the "Register Cinema Hall" option from the menu.
   * It changes the scene to the createCinemaHall.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void registerCinemaHall(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createCinemaHall.fxml");
  }

  /**
   * Handles the action when the user selects the "Register Genre" option from the menu.
   * It changes the scene to the createGenre.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void registerGenre(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createGenre.fxml");
  }

  /**
   * Handles the action when the user selects the "Register Movie" option from the menu.
   * It changes the scene to the createMovie.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void registerMovie(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createMovie.fxml");
  }

  /**
   * Handles the action when the user selects the "Register Movie Session" option from the menu.
   * It changes the scene to the createMovieSession.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void registerMovieSession(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/createMovieSession.fxml");
  }

  /**
   * Handles the action when the user selects the "List Cinema Halls" option from the menu.
   * It changes the scene to the listCinemaHalls.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void listCinemaHalls(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/listCinemaHalls.fxml");
  }

  /**
   * Handles the action when the user selects the "List Movie Sessions" option from the menu.
   * It changes the scene to the listMovieSessions.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void listMovieSession(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/listMovieSessions.fxml");
  }

  /**
   * Handles the action when the user selects the "List Movies" option from the menu.
   * It changes the scene to the listMovies.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void listMovies(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/listMovies.fxml");
  }

  /**
   * Handles the action when the user selects the "Register Product" option from the menu.
   * It changes the scene to the createProduct.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void registerProduct(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/products/createProduct.fxml");
  }

  /**
   * Handles the action when the user selects the "List Products" option from the menu.
   * It changes the scene to the listProducts.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void listProducts(ActionEvent event) throws Exception{
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/products/listProducts.fxml");
  }

  /**
   * Handles the action when the user selects the "List Available Sessions" option from the menu.
   * It changes the scene to the listTicketsInfos.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void listAvailableSessions(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/products/listTicketsInfos.fxml");
  }

  @FXML
  void listCart(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/sale/listCart.fxml");
  }

  @FXML
  void listProductsToSale(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/sales/listProductsToSale.fxml");
  }
}
