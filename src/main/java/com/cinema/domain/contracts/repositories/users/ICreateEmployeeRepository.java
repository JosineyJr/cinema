package com.cinema.domain.contracts.repositories.users;

import com.cinema.domain.entities.users.Employee;

public interface ICreateEmployeeRepository {
  public void createEmployee(Employee employee);
}
