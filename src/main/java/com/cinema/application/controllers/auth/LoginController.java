package com.cinema.application.controllers.auth;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.auth.LoginDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.usecases.auth.LoginUseCase;

public class LoginController extends Controller<LoginDTO> {
  private LoginUseCase loginUseCase;

  public LoginController(LoginUseCase loginUseCase) {
    this.loginUseCase = loginUseCase;
  }

  /**
    * Performs the login operation using the provided LoginDTO object.
    *
    * @param object The LoginDTO object containing the user's credentials.
    * @return A Response object containing the result of the login operation.
    */
  @Override
  public Response<?> perform(LoginDTO object) {
    try {

      String role = this.loginUseCase.execute(object.getCPF(), object.getPassword(), object.isEmployee());

      return ResponseFactory.ok(role);
    } catch (Exception e) {
      return ResponseFactory.unauthorized(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given LoginDTO object.
   *
   * @param object The LoginDTO object to validate.
   * @return An ArrayList of IValidator objects representing the validators for the LoginDTO object.
   */
  @Override
  public ArrayList<IValidator> buildValidators(LoginDTO object) {

    Field CPF = new Field(object.getCPF(), "CPF");
    Field password = new Field(object.getPassword(), "Senha");

    ArrayList<Field> requiredFields = new ArrayList<>();

    requiredFields.add(CPF);
    requiredFields.add(password);

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    validators.addAll(ValidationBuilder.of().required(requiredFields).build());

    return validators;
  }
}
