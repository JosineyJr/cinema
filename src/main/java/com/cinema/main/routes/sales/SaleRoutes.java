package com.cinema.main.routes.sales;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.application.dtos.sales.CompleteSaleDTO;
import com.cinema.main.adapters.SpringAdapter;
import com.cinema.main.factories.sales.CompleteSaleFactory;
import com.cinema.main.factories.sales.ListSalesFactory;

@RestController
public class SaleRoutes {
  private final SpringAdapter<CompleteSaleDTO> completeSaleAdapter;
  private final SpringAdapter<Object> listSalesAdapter;

  public SaleRoutes() {
    this.listSalesAdapter = new SpringAdapter<>(ListSalesFactory.make());
    this.completeSaleAdapter = new SpringAdapter<>(CompleteSaleFactory.make());
  }

  @PostMapping("/complete-sale")
  public ResponseEntity<?> completeSale(@RequestBody CompleteSaleDTO sale) {
    return this.completeSaleAdapter.adapt(sale);
  }

  @GetMapping("/list-sales")
  public ResponseEntity<?> listSales() {
    return listSalesAdapter.adapt(null);
  }
}
