package com.cinema.application.controllers.users;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.users.CreateClientDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.errors.users.ClientAlreadyExistsError;
import com.cinema.domain.usecases.users.CreateClientUseCase;

public class CreateClientController extends Controller {
  private CreateClientUseCase createClientUseCase;

  public CreateClientController(CreateClientUseCase createClientUseCase) {
    this.createClientUseCase = createClientUseCase;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Response perform(Object object) {
    try {
      CreateClientDTO createClientDTO = (CreateClientDTO) object;

      this.createClientUseCase.execute(
          createClientDTO.getFirstName(),
          createClientDTO.getLastName(),
          createClientDTO.getCPF(),
          createClientDTO.getPassword(),
          createClientDTO.getMoviesPreferences());

      return ResponseFactory.noContent();
    } catch (ClientAlreadyExistsError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    CreateClientDTO createClientDTO = (CreateClientDTO) object;

    Field firstName = new Field(createClientDTO.getFirstName(), "Nome");
    Field lastName = new Field(createClientDTO.getLastName(), "Sobrenome");
    Field CPF = new Field(createClientDTO.getCPF(), "CPF");
    Field password = new Field(createClientDTO.getPassword(), "Senha");
    Field passwordConfirmation = new Field(createClientDTO.getPasswordConfirmation(), "Confirmação de Senha");

    ArrayList<Field> requiredFields = new ArrayList<>();

    requiredFields.add(firstName);
    requiredFields.add(lastName);
    requiredFields.add(CPF);
    requiredFields.add(password);
    requiredFields.add(passwordConfirmation);

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    validators.addAll(ValidationBuilder.of().required(requiredFields).validateCPF(CPF)
        .compareFields(password, passwordConfirmation).minimumSize(password, 6).build());

    return validators;
  }
}
