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
