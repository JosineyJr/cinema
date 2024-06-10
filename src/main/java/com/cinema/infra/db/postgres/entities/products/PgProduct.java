package com.cinema.infra.db.postgres.entities.products;

import java.util.UUID;

import com.cinema.infra.db.postgres.entities.sale.PgCart;
import com.cinema.infra.db.postgres.entities.sale.PgSale;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "product")
public class PgProduct {
  @Id
  @GeneratedValue
  private UUID ID;

  @ManyToOne
  @JoinColumn(name = "product_infos_id", nullable = false)
  private PgProductInfos productInfos;

  @ManyToOne
  @JoinColumn(name = "cart_id", nullable = true)
  private PgCart cart;

  @ManyToOne
  @JoinColumn(name = "sale_id", nullable = true)
  private PgSale sale;

  public PgProduct() {
  }

  public PgProduct(UUID ID, PgProductInfos productInfos, PgCart cart) {
    this.ID = ID;
    this.productInfos = productInfos;
    this.cart = cart;
  }

  public PgProduct(PgProductInfos productInfos, PgCart cart) {
    this.productInfos = productInfos;
    this.cart = cart;
  }

  public UUID getID() {
    return this.ID;
  }

  public void setID(UUID ID) {
    this.ID = ID;
  }

  public PgProductInfos getProductInfos() {
    return this.productInfos;
  }

  public void setProductInfos(PgProductInfos productInfos) {
    this.productInfos = productInfos;
  }

  public PgCart getCart() {
    return this.cart;
  }

  public void setCart(PgCart cart) {
    this.cart = cart;
  }

}
