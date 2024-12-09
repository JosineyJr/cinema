package com.cinema.main.routes.users;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.application.dtos.users.CreateClientDTO;
import com.cinema.application.dtos.users.UpdateClientDTO;
import com.cinema.main.adapters.SpringAdapter;
import com.cinema.main.factories.users.CreateClientFactory;
import com.cinema.main.factories.users.ListClientsFactory;
import com.cinema.main.factories.users.UpdateClientFactory;


@RestController
public class ClientRoutes {

  private final SpringAdapter<Object> listClientAdapter;
  private final SpringAdapter<CreateClientDTO> createClientAdapter;
  private final SpringAdapter<UpdateClientDTO> updateClientAdapter;

  public ClientRoutes() {
    this.listClientAdapter = new SpringAdapter<>(ListClientsFactory.make());
    this.createClientAdapter = new SpringAdapter<>(CreateClientFactory.make());
    this.updateClientAdapter = new SpringAdapter<>(UpdateClientFactory.make());
  }

  @GetMapping("/clients")
  public ResponseEntity<?> getClients() {
    return this.listClientAdapter.adapt(null);
  }

  @PostMapping("/client")
  public ResponseEntity<?> postClient(@RequestBody CreateClientDTO client) {
    return createClientAdapter.adapt(client);
  }

  @PutMapping("/client")
  public ResponseEntity<?> updateClient(@RequestBody UpdateClientDTO client) {
    return updateClientAdapter.adapt(client);
  }
}
