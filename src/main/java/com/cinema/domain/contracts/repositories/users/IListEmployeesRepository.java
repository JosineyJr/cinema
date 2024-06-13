package com.cinema.domain.contracts.repositories.users;

import java.util.List;

import com.cinema.domain.entities.users.Employee;

public interface IListEmployeesRepository {
  List<Employee> listEmployees();
}
