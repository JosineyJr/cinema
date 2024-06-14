package com.cinema.domain.entities.financial;

import java.time.LocalDate;

import com.cinema.domain.entities.sale.SalesCounter;

public class DailySalesReport {
  private LocalDate saleDay;
    private SalesCounter salesCounter;
    private Long totalSales;
    private Double totalPrice;

    public DailySalesReport(LocalDate saleDay, SalesCounter salesCounter, Long totalSales, Double totalPrice) {
        this.saleDay = saleDay;
        this.salesCounter = salesCounter;
        this.totalSales = totalSales;
        this.totalPrice = totalPrice;
    }

    public LocalDate getSaleDay() {
        return saleDay;
    }

    public void setSaleDay(LocalDate saleDay) {
        this.saleDay = saleDay;
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

    public void setTotalSales(Long totalSales) {
        this.totalSales = totalSales;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
