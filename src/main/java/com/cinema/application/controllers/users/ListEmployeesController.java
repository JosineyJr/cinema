package com.cinema.application.controllers.users;

import java.util.ArrayList;
import java.util.List;

import com.cinema.application.controllers.Controller;
import com.cinema.application.dtos.users.EmployeeDTO;
import com.cinema.application.helpers.Response;
import com.cinema.application.helpers.ResponseFactory;
import com.cinema.application.validation.IValidator;
import com.cinema.domain.entities.users.Employee;
import com.cinema.domain.usecases.users.ListEmployeesUseCase;

public class ListEmployeesController extends Controller<Object> {
  private ListEmployeesUseCase listEmployeesUseCase;

  public ListEmployeesController(ListEmployeesUseCase listEmployeesUseCase) {
    this.listEmployeesUseCase = listEmployeesUseCase;
  }

  /**
   * Performs the necessary operations to retrieve a list of employees and convert them into EmployeeDTO objects.
   *
   * @param object An optional parameter that can be passed to the method.
   * @return A Response object containing a list of EmployeeDTO objects if successful, or an error response if an exception occurs.
   */
  public Response<?> perform(Object object) {
    try {
      List<Employee> employees = this.listEmployeesUseCase.execute();

      List<EmployeeDTO> employeeDTOs = employees.stream()
          .map(
              employee -> new EmployeeDTO(employee.getID(), employee.getFirstName(), employee.getLastName(),
                  employee.getCPF()))
          .toList();

      return ResponseFactory.ok(employeeDTOs);
    } catch (Exception e) {
      return ResponseFactory.serverError(e);
    }
  }

  /**
   * Builds and returns a list of validators for the given object.
   *
   * @param object the object for which validators need to be built
   * @return an ArrayList of IValidator objects representing the validators
   */
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }
}
