package com.cinema.main.routes.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.main.adapters.SpringAdapter;
import com.cinema.main.factories.users.ListPersonsFactory;

@RestController
public class PersonRoutes {
  private final SpringAdapter<Object> listPersonsAdapter;

  public PersonRoutes() {
    this.listPersonsAdapter = new SpringAdapter<>(ListPersonsFactory.make());
  }

  @GetMapping("/persons")
  public ResponseEntity<?> getEmployees() {
    return this.listPersonsAdapter.adapt(null);
  }
}
