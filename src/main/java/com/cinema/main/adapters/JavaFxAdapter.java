package com.cinema.main.adapters;

import com.cinema.application.controllers.Controller;
import com.cinema.application.helpers.Response;

public class JavaFxAdapter {
  @SuppressWarnings("rawtypes")
  public static Response adaptResolver(Controller controller, Object args) {
    return controller.handle(args);
  }
}
