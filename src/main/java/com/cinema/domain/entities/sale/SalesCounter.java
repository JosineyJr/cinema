package com.cinema.domain.entities.sale;

import java.util.UUID;

import com.cinema.domain.enums.sale.SalesCounterType;

import java.util.List;

public class SalesCounter {
  private UUID ID;

  private SalesCounterType type;

  private boolean isAvailable;

  private List<Sale> sales;

  public SalesCounter(UUID ID, SalesCounterType type, boolean isAvailable, List<Sale> sales) {
    this.ID = ID;
    this.type = type;
    this.isAvailable = isAvailable;
    this.sales = sales;
  }

  public SalesCounter(SalesCounterType type, boolean isAvailable, List<Sale> sales) {
    this.type = type;
    this.isAvailable = isAvailable;
    this.sales = sales;
  }

  public SalesCounter(SalesCounterType type, boolean isAvailable) {
    this.type = type;
    this.isAvailable = isAvailable;
  }

  public SalesCounter(UUID ID, SalesCounterType type, boolean isAvailable) {
    this.ID = ID;
    this.type = type;
    this.isAvailable = isAvailable;
  }

  public SalesCounter(UUID ID) {
    this.ID = ID;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public SalesCounterType getType() {
    return this.type;
  }

  public void setType(SalesCounterType type) {
    this.type = type;
  }

  public List<Sale> getSales() {
    return this.sales;
  }

  public void setSales(List<Sale> sales) {
    this.sales = sales;
  }

  public boolean isAvailable() {
    return this.isAvailable;
  }

  public void setAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }
}
