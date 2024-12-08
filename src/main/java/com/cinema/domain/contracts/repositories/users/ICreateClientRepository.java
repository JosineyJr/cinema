package com.cinema.domain.contracts.repositories.users;

import com.cinema.domain.entities.users.Client;

public interface ICreateClientRepository {
  public void createClient(Client client);
}
