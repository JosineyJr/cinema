package com.cinema.domain.usecases.users;

import com.cinema.domain.contracts.providers.IHasher;
import com.cinema.domain.contracts.repositories.users.ICreateAdminRepository;
import com.cinema.domain.contracts.repositories.users.ICreateEmployeeRepository;
import com.cinema.domain.contracts.repositories.users.IFindEmployeeByCPFRepository;
import com.cinema.domain.entities.users.Admin;
import com.cinema.domain.entities.users.Employee;
import com.cinema.domain.errors.users.EmployeeAlreadyExistsError;

public class CreateEmployeeUseCase {
  IFindEmployeeByCPFRepository findEmployeeByCPFRepository;
  IHasher hasher;
  ICreateEmployeeRepository createEmployeeRepository;
  ICreateAdminRepository createAdminRepository;

  public CreateEmployeeUseCase(IFindEmployeeByCPFRepository findEmployeeByCPFRepository, IHasher hasher,
      ICreateEmployeeRepository createEmployeeRepository, ICreateAdminRepository createAdminRepository) {
    this.findEmployeeByCPFRepository = findEmployeeByCPFRepository;
    this.hasher = hasher;
    this.createEmployeeRepository = createEmployeeRepository;
    this.createAdminRepository = createAdminRepository;
  }

  /**
   * Executes the use case to create a new employee.
   *
   * @param firstName the first name of the employee
   * @param lastName the last name of the employee
   * @param CPF the CPF (Brazilian identification number) of the employee
   * @param password the password of the employee
   * @param isAdmin a boolean indicating whether the employee is an admin or not
   * @throws EmployeeAlreadyExistsError if an employee with the same CPF already exists
   */
  public void execute(
      String firstName,
      String lastName,
      String CPF,
      String password,
      boolean isAdmin) throws EmployeeAlreadyExistsError {
    if (findEmployeeByCPFRepository.findEmployeeByCPF(CPF) != null) {
      throw new EmployeeAlreadyExistsError();
    }

    String hashedPassword = this.hasher.hash(password);

    if(isAdmin){
      this.createAdminRepository.createAdmin(new Admin(firstName, lastName, CPF, hashedPassword));
    } else {
      this.createEmployeeRepository.createEmployee(new Employee(firstName, lastName, CPF, hashedPassword));
    }
  }
}
