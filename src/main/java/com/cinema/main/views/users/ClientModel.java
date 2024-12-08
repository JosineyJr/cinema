package com.cinema.main.views.users;

import com.cinema.application.dtos.users.ClientDTO;

public class ClientModel {
  private static ClientModel clientModel = null;
  private ClientDTO client;

  private ClientModel() {
  }

  public static ClientModel getInstance() {
    if (clientModel == null) {
      clientModel = new ClientModel();
    }

    return clientModel;
  }

  public ClientDTO getClient() {
    return client;
  }

  public void setClient(ClientDTO client) {
    this.client = client;
  }
}