package com.cinema.domain.contracts.repositories.users;

import com.cinema.domain.entities.users.Client;

public interface IUpdateClientRepository {
  void updateClient(Client client);
}
