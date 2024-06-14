package com.cinema.main.views;

import com.cinema.main.views.helpers.ChangeWindow;
import com.cinema.main.views.helpers.Session;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.stage.Stage;

/**
 * The MenuView class represents the view for the main menu of the cinema
 * application.
 * It contains methods for handling user actions related to different menu
 * options.
 */
public class MenuView {
  @FXML
  private Menu movies;

  @FXML
  private Menu products;

  @FXML
  private Menu cart;

  @FXML
  private Menu profile;

  @FXML
  private Menu management;

  @FXML
  private Menu sales;

  @FXML
  private Menu balcony;

  @FXML
  private Menu employees;

  @FXML
  private Menu financial;

  /**
   * Initializes the MenuView.
   * This method is automatically called by JavaFX after the FXML file has been
   * loaded.
   * It prints the role of the current session to the console.
   */
  @FXML
  public void initialize() {
    if (Session.getPersonId() == null) {
      management.setVisible(false);
      movies.setVisible(false);
      products.setVisible(false);
      profile.setVisible(false);
      cart.setVisible(false);
      
      return;
    }

    switch (Session.getRole()) {
      case "employee":
        sales.setVisible(false);
        balcony.setVisible(false);
        employees.setVisible(false);
        financial.setVisible(false);

        break;
      case "client":
        management.setVisible(false);

        break;
      default:
        break;
    }
  }

  /**
   * Handles the action when the user selects the "List Genres" option from the
   * menu.
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
   * Handles the action when the user selects the "Register Cinema Hall" option
   * from the menu.
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
   * Handles the action when the user selects the "Register Genre" option from the
   * menu.
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
   * Handles the action when the user selects the "Register Movie" option from the
   * menu.
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
   * Handles the action when the user selects the "Register Movie Session" option
   * from the menu.
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
   * Handles the action when the user selects the "List Cinema Halls" option from
   * the menu.
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
   * Handles the action when the user selects the "List Movie Sessions" option
   * from the menu.
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
   * Handles the action when the user selects the "List Movies" option from the
   * menu.
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
   * Handles the action when the user selects the "Register Product" option from
   * the menu.
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
   * Handles the action when the user selects the "List Products" option from the
   * menu.
   * It changes the scene to the listProducts.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void listProducts(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/products/listProducts.fxml");
  }

  /**
   * Handles the action when the user selects the "List Available Sessions" option
   * from the menu.
   * It changes the scene to the listTickets.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void listAvailableSessions(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/sales/listMoviesToSale.fxml");
  }

  /**
   * Handles the action when the user selects the "List Sales" option from the
   * menu.
   * It changes the scene to the listSales.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void listSales(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/sales/listSales.fxml");
  }

  /**
   * Handles the action when the user selects the "Register Employee" option from
   * the menu.
   * It changes the scene to the createEmployee.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void registerEmployee(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/users/createEmployee.fxml");
  }

  /**
   * Handles the action when the user selects the "List Users" option from the
   * menu.
   * It changes the scene to the listUsers.fxml view.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void listClients(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/users/listClients.fxml");
  }

  /**
   * Event handler for the "listCart" button.
   * This method is called when the button is clicked and is responsible for
   * changing the scene to the "listCart.fxml" view.
   * It retrieves the primary stage using the StageManager class and calls the
   * ChangeWindow.changeScene method to change the scene.
   *
   * @param event The event that triggered the method call.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void listCart(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/sales/listCart.fxml");
  }

  /**
   * Event handler for the "listProductsToSale" button.
   * This method is called when the button is clicked and is responsible for
   * changing the scene to the "listProductsToSale" view.
   * 
   * @param event The event that triggered the method.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void listProductsToSale(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/sales/listProductsToSale.fxml");
  }

  /**
   * Displays the user's profile.
   *
   * @param event the action event that triggered the method
   * @throws Exception if an error occurs while displaying the profile
   */
  @FXML
  void showProfile(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/users/profile.fxml");
  }

  /**
   * Handles the event when the register client button is clicked.
   * This method changes the scene to the create client view.
   *
   * @param event The action event triggered by clicking the register client
   *              button.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void registerClient(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/users/createClient.fxml");
  }

  /**
   * Event handler for the "listEmployees" button.
   * This method is called when the button is clicked and it opens a new window to
   * display a list of employees.
   *
   * @param event The event triggered by clicking the button.
   * @throws Exception If an error occurs while changing the scene.
   */
  @FXML
  void listEmployees(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/users/listEmployees.fxml");
  }

  /**
   * Handles the action event for generating income reports.
   *
   * @param event The action event triggered by the user.
   * @throws Exception If an error occurs while generating the income reports.
   */
  @FXML
  void incomeReports(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/financial/incomeReports.fxml");
  }

  @FXML
  void expenseReports(ActionEvent event) throws Exception {
    Stage primaryStage = StageManager.getPrimaryStage();
    ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/financial/expenseReports.fxml");
  }
}
