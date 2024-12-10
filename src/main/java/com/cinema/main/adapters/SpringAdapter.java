package com.cinema.main.adapters;

import org.springframework.http.ResponseEntity;

import com.cinema.application.controllers.Controller;
import com.cinema.application.helpers.Response;

import org.springframework.http.HttpStatus;

public class SpringAdapter<T> {

  private final Controller<T> controller;

  public SpringAdapter(Controller<T> controller) {
    this.controller = controller;
  }

  public ResponseEntity<?> adapt(T input) {
    Response<?> response = controller.handle(input);

    int statusCode = response.getStatusCode();
    Object data = response.getData();


    System.out.println(data);
    System.out.println(statusCode);
    // Converte o código num HttpStatus
    HttpStatus httpStatus = HttpStatus.valueOf(statusCode);

    // Caso seja sucesso (200 ou 204), retorna data diretamente
    if (statusCode == 200 || statusCode == 204) {
      return ResponseEntity.status(httpStatus).body(data);
    } else {
      // Caso contrário, assume que data é um erro
      // Se 'data' for uma Exception, pega sua mensagem
      String errorMessage = (data instanceof Exception) ? ((Exception) data).getMessage() : data.toString();
      return ResponseEntity.status(httpStatus).body(
          // Pode retornar um Map para padronizar o erro
          java.util.Map.of("error", errorMessage));
    }
  }
}
