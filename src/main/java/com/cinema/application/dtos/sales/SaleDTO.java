package com.cinema.application.dtos.sales;

import java.util.List;
import java.util.UUID;

public class SaleDTO {
  private UUID ID;
  private List<SaleItemDTO> items;
  private String date;
  private String CPF;
  private double total;
  private String salesCounter;

  public SaleDTO() {
  }

  public SaleDTO(UUID ID, List<SaleItemDTO> items, String date, String CPF, double total,
      String salesCounter) {
    this.ID = ID;
    this.items = items;
    this.date = date;
    this.CPF = CPF;
    this.total = total;
    this.salesCounter = salesCounter;
  }

  public UUID getID() {
    return ID;
  }

  public List<SaleItemDTO> getItems() {
    return items;
  }

  public String getDate() {
    return date;
  }

  public String getCPF() {
    return CPF;
  }

  public double getTotal() {
    return total;
  }

  public String getSalesCounter() {
    return salesCounter;
  }
}
