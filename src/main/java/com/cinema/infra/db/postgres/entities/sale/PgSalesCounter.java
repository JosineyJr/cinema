package com.cinema.infra.db.postgres.entities.sale;

import java.util.UUID;
import java.util.List;

import com.cinema.domain.enums.sale.SalesCounterType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "sales_counter")
public class PgSalesCounter {
  @Id
  @GeneratedValue
  private UUID ID;

  @Column(nullable = false)
  private SalesCounterType type;

  @Column(nullable = false, name = "is_available")
  private boolean isAvailable;

  @OneToMany(mappedBy = "salesCounter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<PgSale> sales;

  public PgSalesCounter() {
  }

  public PgSalesCounter(UUID ID, SalesCounterType type, boolean isAvailable, List<PgSale> sales) {
    this.ID = ID;
    this.type = type;
    this.isAvailable = isAvailable;
    this.sales = sales;
  }

  public PgSalesCounter(SalesCounterType type, boolean isAvailable) {
    this.type = type;
    this.isAvailable = isAvailable;
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

  public List<PgSale> getSales() {
    return this.sales;
  }

  public void setSales(List<PgSale> sales) {
    this.sales = sales;
  }

  public boolean isIsAvailable() {
    return this.isAvailable;
  }

  public boolean getIsAvailable() {
    return this.isAvailable;
  }
}
