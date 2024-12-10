package com.cinema.main.routes.financial;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cinema.main.adapters.SpringAdapter;
import com.cinema.main.factories.financial.GetDailySalesFactory;
import com.cinema.main.factories.financial.GetMonthlySalesFactory;

@RestController
public class SalesReportRoutes {

  private final SpringAdapter<Object> listDailySalesAdapter;
  private final SpringAdapter<Object> listMonthlySalesAdapter;

  public SalesReportRoutes() {
    this.listDailySalesAdapter = new SpringAdapter<>(GetDailySalesFactory.make());
    this.listMonthlySalesAdapter = new SpringAdapter<>(GetMonthlySalesFactory.make());
  }

  @GetMapping("/daily-sales")
  public ResponseEntity<?> getDailySales() {
    return this.listDailySalesAdapter.adapt(null);
  }

  @GetMapping("/monthly-sales")
  public ResponseEntity<?> getMonthlySales() {
    return this.listMonthlySalesAdapter.adapt(null);
  }
}
