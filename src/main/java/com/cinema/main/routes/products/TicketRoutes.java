package com.cinema.main.routes.products;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.main.adapters.SpringAdapter;
import com.cinema.main.factories.products.ListTicketsFactory;

@RestController
public class TicketRoutes {
  private final SpringAdapter<Object> listTicketsAdapter;

  public TicketRoutes() {
    this.listTicketsAdapter = new SpringAdapter<>(ListTicketsFactory.make());
  }

  @GetMapping("/tickets")
  public ResponseEntity<?> getProducts() {
    return this.listTicketsAdapter.adapt(null);
  }
}
