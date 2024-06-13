package com.cinema.infra.db.postgres.entities.sale;

import java.util.UUID;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity(name = "sale")
public class PgSale {
  @Id
  @GeneratedValue
  private UUID ID;

  @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<PgProductSale> products;

  @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<PgTicketSale> tickets;

  @Column(name = "total_price", nullable = false)
  private double totalPrice;

  @Column(name = "sale_date", nullable = false)
  private LocalDateTime saleDate;

  @ManyToOne()
  @JoinColumn(nullable = true, name = "sales_counter_id")
  private PgSalesCounter sales_counter;

  public PgSale() {
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public double getTotalPrice() {
    return this.totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

  public LocalDateTime getSaleDate() {
    return this.saleDate;
  }

  public void setSaleDate(LocalDateTime saleDate) {
    this.saleDate = saleDate;
  }
}
