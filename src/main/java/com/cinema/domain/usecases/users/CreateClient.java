package com.cinema.domain.usecases.users;

import java.util.ArrayList;

import com.cinema.domain.contracts.repositories.users.ICreateClientRepository;
import com.cinema.domain.entities.users.Client;

public class CreateClient {
  ICreateClientRepository createClient;

  public CreateClient(ICreateClientRepository createClient) {
    this.createClient = createClient;
  }

  public void execute(
      String firstName,
      String lastName,
      String CPF,
      ArrayList<String> moviesPreferences) {

    Client client = new Client(firstName, lastName, CPF, moviesPreferences);

    createClient.createClient(client);
  }
}
