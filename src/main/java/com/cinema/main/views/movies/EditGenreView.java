package com.cinema.main.views.movies;

import com.cinema.application.dtos.movies.GenreDTO;
import com.cinema.application.dtos.movies.UpdateGenreDTO;
import com.cinema.application.helpers.Response;
import com.cinema.main.factories.movies.UpdateGenreFactory;
import com.cinema.main.views.StageManager;
import com.cinema.main.views.helpers.AlertError;
import com.cinema.main.views.helpers.AlertSuccess;
import com.cinema.main.views.helpers.ChangeWindow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditGenreView {
  @FXML
  private TextField genreNameField;

  private GenreDTO genre;

  @FXML
  void initialize() {
    genre = GenreModel.getInstance().getGenre();

    genreNameField.setText(genre.getName());
  }

  @FXML
  void editGenre(ActionEvent event) throws Exception{
    GenreDTO genre = new GenreDTO(this.genre.getID(), genreNameField.getText());

    UpdateGenreDTO updateGenreDTO = new UpdateGenreDTO(genre.getID().toString(), genreNameField.getText());

    Response<?> response = UpdateGenreFactory.make().handle(updateGenreDTO);

    if (response.getStatusCode() == 204) {
      new AlertSuccess("Gênero editado com sucesso!");

      Stage primaryStage = StageManager.getPrimaryStage();

      ChangeWindow.changeScene(primaryStage, "/com/cinema/main/views/movies/listGenres.fxml");
    } else {
      new AlertError(response.getData().toString());
    }
  }
}
