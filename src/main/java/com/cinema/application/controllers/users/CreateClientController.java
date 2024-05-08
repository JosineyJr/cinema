package com.cinema.application.controllers.users;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.CreateClientDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.entities.users.errors.ClientAlreadyExistsError;
import com.cinema.domain.usecases.users.CreateClientUseCase;

public class CreateClientController extends Controller {
  public CreateClientUseCase createClientUseCase;

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

    String firstName = createClientDTO.getFirstName();
    String lastName = createClientDTO.getLastName();
    String CPF = createClientDTO.getCPF();
    String password = createClientDTO.getPassword();
    String passwordConfirmation = createClientDTO.getPasswordConfirmation();

    ArrayList<Field> requiredFields = new ArrayList<Field>();

    requiredFields.add(new Field(firstName, "firstName"));
    requiredFields.add(new Field(lastName, "lastName"));
    requiredFields.add(new Field(CPF, "CPF"));
    requiredFields.add(new Field(password, "password"));
    requiredFields.add(new Field(passwordConfirmation, "passwordConfirmation"));

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    validators.addAll(ValidationBuilder.of().required(requiredFields).build());

    validators.addAll(ValidationBuilder.of().validateCPF(new Field(CPF, "CPF")).build());

    validators.addAll(ValidationBuilder.of().compareFields(new Field(password, "password"),new Field(passwordConfirmation, "passwordConfirmation")).build());

    return validators;
  }
}
