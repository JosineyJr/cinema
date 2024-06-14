package com.cinema.domain.entities.financial;

import com.cinema.domain.entities.sale.SalesCounter;

public class MonthlySalesReport {
  private SalesCounter salesCounter;
  private Long totalSales;
  private Double totalPrice;

  public MonthlySalesReport(SalesCounter salesCounter, Long totalSales, Double totalPrice) {
    this.salesCounter = salesCounter;
    this.totalSales = totalSales;
    this.totalPrice = totalPrice;
  }

  public SalesCounter getSalesCounter() {
    return salesCounter;
  }

  public void setSalesCounter(SalesCounter salesCounter) {
    this.salesCounter = salesCounter;
  }

  public Long getTotalSales() {
    return totalSales;
  }
  
  public Double getTotalPrice() {
    return totalPrice;
  }
}
