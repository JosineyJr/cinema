package com.cinema.application.controllers.users;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.users.PersonDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.users.Admin;
import com.cinema.domain.entities.users.Client;
import com.cinema.domain.entities.users.Person;
import com.cinema.domain.enums.auth.Role;
import com.cinema.domain.usecases.users.ListPersonsUseCase;

public class ListPersonsController extends Controller<Object> {
  private ListPersonsUseCase listPersonsUseCase;

  public ListPersonsController(ListPersonsUseCase listPersonsUseCase) {
    this.listPersonsUseCase = listPersonsUseCase;
  }

  @Override
  public Response<?> perform(Object object) {
    try {
      List<Person> response = this.listPersonsUseCase.execute();

      List<PersonDTO> personsDTO = new ArrayList<PersonDTO>();

      for (Person person : response) {
        if (person instanceof Admin) {
          personsDTO.add(
              new PersonDTO(person.getID(), person.getFirstName(), person.getLastName(), person.getCPF(), Role.ADMIN));
        } else if (person instanceof Client) {
          personsDTO.add(
              new PersonDTO(person.getID(), person.getFirstName(), person.getLastName(), person.getCPF(), Role.CLIENT));
        } else {
          personsDTO.add(
              new PersonDTO(person.getID(), person.getFirstName(), person.getLastName(), person.getCPF(),
                  Role.EMPLOYEE));
        }
      }

      return ResponseFactory.ok(personsDTO);
    } catch (Exception e) {
      throw e;
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }
}
