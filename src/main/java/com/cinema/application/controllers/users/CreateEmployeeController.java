package com.cinema.application.controllers.users;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.users.CreateEmployeeDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.usecases.users.CreateEmployeeUseCase;

public class CreateEmployeeController extends Controller {
  private CreateEmployeeUseCase createEmployeeUseCase;

  public CreateEmployeeController(CreateEmployeeUseCase createEmployeeUseCase) {
    this.createEmployeeUseCase = createEmployeeUseCase;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Response perform(Object object) {
    try {
      CreateEmployeeDTO createEmployeeDTO = (CreateEmployeeDTO) object;

      this.createEmployeeUseCase.execute(createEmployeeDTO.getFirstName(), createEmployeeDTO.getLastName(),
          createEmployeeDTO.getCPF(), createEmployeeDTO.getPassword(), createEmployeeDTO.isAdmin());

      return ResponseFactory.noContent();
    } catch (Exception e) {
      return ResponseFactory.badRequest(e);
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    CreateEmployeeDTO createEmployeeDTO = (CreateEmployeeDTO) object;

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    Field firstName = new Field(createEmployeeDTO.getFirstName(), "Nome");
    Field lastName = new Field(createEmployeeDTO.getLastName(), "Sobrenome");
    Field CPF = new Field(createEmployeeDTO.getCPF(), "CPF");
    Field password = new Field(createEmployeeDTO.getPassword(), "Senha");
    Field passwordConfirmation = new Field(createEmployeeDTO.getPasswordConfirmation(), "Confirmação de Senha");

    ArrayList<Field> requiredFields = new ArrayList<>();

    requiredFields.add(firstName);
    requiredFields.add(lastName);
    requiredFields.add(CPF);
    requiredFields.add(password);
    requiredFields.add(passwordConfirmation);

    validators.addAll(ValidationBuilder.of().required(requiredFields).compareFields(password, passwordConfirmation)
        .validateCPF(CPF).minimumSize(passwordConfirmation, 6).build());

    return validators;
  }

}
