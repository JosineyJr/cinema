package com.cinema.main.routes.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.application.dtos.users.CreateEmployeeDTO;
import com.cinema.main.adapters.SpringAdapter;
import com.cinema.main.factories.users.CreateEmployeeFactory;
import com.cinema.main.factories.users.ListEmployeesFactory;

@RestController
public class EmployeeRoutes {

  private final SpringAdapter<Object> listEmployeeAdapter;
  private final SpringAdapter<CreateEmployeeDTO> createEmployeeAdapter;

  public EmployeeRoutes() {
    this.listEmployeeAdapter = new SpringAdapter<>(ListEmployeesFactory.make());
    this.createEmployeeAdapter = new SpringAdapter<>(CreateEmployeeFactory.make());
  }

  @GetMapping("/employees")
  public ResponseEntity<?> getEmployees() {
    return this.listEmployeeAdapter.adapt(null);
  }

  @PostMapping("/employee")
  public ResponseEntity<?> postEmployee(@RequestBody CreateEmployeeDTO Employee) {
    return createEmployeeAdapter.adapt(Employee);
  }
}
