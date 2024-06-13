package com.cinema.domain.usecases.users;

import java.util.List;

import com.cinema.domain.contracts.repositories.users.IListEmployeesRepository;
import com.cinema.domain.entities.users.Employee;

public class ListEmployeesUseCase {
  private IListEmployeesRepository listEmployeesRepository;

  public ListEmployeesUseCase(IListEmployeesRepository listEmployeesRepository) {
    this.listEmployeesRepository = listEmployeesRepository;
  }

  /**
   * Executes the use case to list all employees.
   *
   * @return a list of employees
   */
  public List<Employee> execute(){
    return this.listEmployeesRepository.listEmployees();
  }
}
