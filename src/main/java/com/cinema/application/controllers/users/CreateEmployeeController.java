package com.cinema.application.controllers.users;

import java.util.ArrayList;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.users.CreateEmployeeDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.errors.users.EmployeeAlreadyExistsError;
import com.cinema.domain.usecases.users.CreateEmployeeUseCase;

public class CreateEmployeeController extends Controller<CreateEmployeeDTO> {
  private CreateEmployeeUseCase createEmployeeUseCase;

  public CreateEmployeeController(CreateEmployeeUseCase createEmployeeUseCase) {
    this.createEmployeeUseCase = createEmployeeUseCase;
  }

  /**
    * Performs the creation of a new employee based on the provided CreateEmployeeDTO object.
    *
    * @param object The CreateEmployeeDTO object containing the employee details.
    * @return A Response object indicating the success or failure of the operation.
    */
  @Override
  public Response<?> perform(CreateEmployeeDTO object) {
    try {

      this.createEmployeeUseCase.execute(object.getFirstName(), object.getLastName(), object.getCPF(),
          object.getPassword(),
          object.isAdmin());

      return ResponseFactory.noContent();
    } catch (EmployeeAlreadyExistsError e) {
      return ResponseFactory.badRequest(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given CreateEmployeeDTO object.
   *
   * @param object The CreateEmployeeDTO object to validate.
   * @return An ArrayList of IValidator objects representing the validators for the CreateEmployeeDTO object.
   */
  @Override
  public ArrayList<IValidator> buildValidators(CreateEmployeeDTO object) {
    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    Field firstName = new Field(object.getFirstName(), "Nome");
    Field lastName = new Field(object.getLastName(), "Sobrenome");
    Field CPF = new Field(object.getCPF(), "CPF");
    Field password = new Field(object.getPassword(), "Senha");
    Field passwordConfirmation = new Field(object.getPasswordConfirmation(), "Confirmação de Senha");

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
