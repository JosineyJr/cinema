package com.cinema.application.controllers.users;

import java.util.ArrayList;
import java.util.Arrays;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.users.UpdateClientDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.Field;
import com.cinema.application.validation.IValidator;
import com.cinema.application.validation.ValidationBuilder;
import com.cinema.domain.entities.users.Client;
import com.cinema.domain.errors.users.PersonNotFoundError;
import com.cinema.domain.usecases.users.UpdatePersonUseCase;

public class UpdateClientController extends Controller<UpdateClientDTO> {
  private UpdatePersonUseCase updateClientUseCase;

  public UpdateClientController(UpdatePersonUseCase updateClientUseCase) {
    this.updateClientUseCase = updateClientUseCase;
  }

  /**
   * Performs the update operation for a client based on the provided
   * UpdateClientDTO.
   *
   * @param updateClientDTO The DTO containing the updated client information.
   * @return A Response object indicating the result of the update operation.
   */
  public Response<?> perform(UpdateClientDTO updateClientDTO) {
    try {
      Client client = new Client(updateClientDTO.getID(), updateClientDTO.getFirstName(), updateClientDTO.getLastName(),
          updateClientDTO.getCPF());

      this.updateClientUseCase.execute(client);

      return ResponseFactory.noContent();
    } catch (PersonNotFoundError e) {
      return ResponseFactory.badRequest(e);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return ResponseFactory.serverError(e);
    }
  }

  /**
   * Builds a list of validators for the given UpdateClientDTO object.
   *
   * @param object The UpdateClientDTO object to validate.
   * @return An ArrayList of IValidator objects representing the validators.
   */
  public ArrayList<IValidator> buildValidators(UpdateClientDTO object) {
    Field firstName = new Field(object.getFirstName(), "Nome");
    Field lastName = new Field(object.getLastName(), "Sobrenome");
    Field CPF = new Field(object.getCPF(), "CPF");

    ArrayList<Field> requiredFields = new ArrayList<>(Arrays.asList(
        firstName,
        lastName,
        CPF));

    ArrayList<IValidator> validators = new ArrayList<IValidator>();

    validators.addAll(ValidationBuilder.of().required(requiredFields).validateCPF(CPF).build());

    return validators;
  }
}
