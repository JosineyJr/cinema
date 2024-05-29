package com.cinema.application.controllers.users;

import java.util.ArrayList;
import java.util.Arrays;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.users.CreateClientDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.errors.users.ClientAlreadyExistsError;
import com.cinema.domain.usecases.users.CreateClientUseCase;

public class CreateClientController extends Controller<CreateClientDTO> {
  private CreateClientUseCase createClientUseCase;

  public CreateClientController(CreateClientUseCase createClientUseCase) {
    this.createClientUseCase = createClientUseCase;
  }

  @Override
  public Response<?> perform(CreateClientDTO object) {
    try {
      this.createClientUseCase.execute(
          object.getFirstName(),
          object.getLastName(),
          object.getCPF(),
          object.getPassword(),
          object.getMoviesPreferences());

      return ResponseFactory.noContent();
    } catch (ClientAlreadyExistsError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(CreateClientDTO object) {
    Field firstName = new Field(object.getFirstName(), "Nome");
    Field lastName = new Field(object.getLastName(), "Sobrenome");
    Field CPF = new Field(object.getCPF(), "CPF");
    Field password = new Field(object.getPassword(), "Senha");
    Field passwordConfirmation = new Field(object.getPasswordConfirmation(), "Confirmação de Senha");

    ArrayList<Field> requiredFields = new ArrayList<>(Arrays.asList(
        firstName,
        lastName,
        CPF,
        password,
        passwordConfirmation));

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    validators.addAll(ValidationBuilder.of().required(requiredFields).validateCPF(CPF)
        .compareFields(password, passwordConfirmation).minimumSize(password, 6).build());

    return validators;
  }
}
