package com.cinema.main.routes.sales;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.application.dtos.sales.ChangeAvailableSalesCounterDTO;
import com.cinema.main.adapters.SpringAdapter;
import com.cinema.main.factories.sales.ChangeAvailableSalesCounterFactory;
import com.cinema.main.factories.sales.CreateOrListSalesCounterFactory;

@RestController
public class SalesCounterRoutes {
  private final SpringAdapter<ChangeAvailableSalesCounterDTO> changeAvailableSalesCounterAdapter;
  private final SpringAdapter<Object> createOrListSalesCounterAdapter;

  public SalesCounterRoutes() {
    this.changeAvailableSalesCounterAdapter = new SpringAdapter<>(ChangeAvailableSalesCounterFactory.make());
    this.createOrListSalesCounterAdapter = new SpringAdapter<>(CreateOrListSalesCounterFactory.make());
  }

  @PatchMapping("/change-available-sales-counter")
  public ResponseEntity<?> changeAvailableSalesCounter(@RequestBody ChangeAvailableSalesCounterDTO salesCounter) {
    return this.changeAvailableSalesCounterAdapter.adapt(salesCounter);
  }

  @GetMapping("/create-or-list-sales-counter")
  public ResponseEntity<?> createOrListSalesCounter() {
    return createOrListSalesCounterAdapter.adapt(null);
  }
}
