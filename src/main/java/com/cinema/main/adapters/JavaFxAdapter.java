package com.cinema.main.adapters;

import com.cinema.application.controllers.Controller;
import com.cinema.application.helpers.Response;

import javafx.application.Platform;

public class JavaFxAdapter {
  public static <T> Object adaptResolver(Controller<T> controller, T args) {
    Response<?> response = controller.handle(args);

    if (response.getStatusCode() >= 400) {
      return (String) response.getData();
    }

    return response.getData();
  }

  public static <T> void handle(T object, JavaFXResponseHandler<T> responseHandler) {
    new Thread(() -> {
      Response<?> response = controller.handle(object);

      Platform.runLater(() -> {
        if (response.getStatusCode() == 200) {
          responseHandler.onSuccess((T) response.getData());
        } else {
          responseHandler.onFailure(response.getData());
        }
      });
    }).start();
  }

  public interface JavaFXResponseHandler<T> {
    void onSuccess(T object);

    void onFailure(Object error);
  }
}
