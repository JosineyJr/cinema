package com.cinema.infra.db.postgres.entities.sale;

import java.util.UUID;

import com.cinema.infra.db.postgres.entities.products.PgProduct;
import com.cinema.infra.db.postgres.entities.products.PgTicket;

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
  private List<PgProduct> products;

  @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<PgTicket> tickets;

  @Column(name = "total_price", nullable = false)
  private double totalPrice;

  @Column(name = "sale_date", nullable = false)
  private LocalDateTime saleDate;

  @ManyToOne()
  @JoinColumn(nullable = true, name = "sales_counter_id")
  private PgSalesCounter sales_counter;

  public PgSale() {
  }

  public PgSale(UUID ID, List<PgProduct> products, List<PgTicket> tickets, double totalPrice, LocalDateTime saleDate,
      PgSalesCounter sales_counter) {
    this.ID = ID;
    this.products = products;
    this.tickets = tickets;
    this.totalPrice = totalPrice;
    this.saleDate = saleDate;
    this.sales_counter = sales_counter;
  }

  public PgSale(List<PgProduct> products, List<PgTicket> tickets, double totalPrice, LocalDateTime saleDate,
      PgSalesCounter sales_counter) {
    this.products = products;
    this.tickets = tickets;
    this.totalPrice = totalPrice;
    this.saleDate = saleDate;
    this.sales_counter = sales_counter;
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
