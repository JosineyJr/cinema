package com.cinema.application.dtos.sales;

import java.util.List;
import java.util.UUID;

public class CompleteSaleDTO {
  private List<ProductsCartDTO> productsCart;

  private List<TicketsCartDTO> ticketsCart;

  private UUID personID;

  public CompleteSaleDTO(List<ProductsCartDTO> productsCart, List<TicketsCartDTO> ticketsCart,
      UUID personID) {
    this.productsCart = productsCart;
    this.ticketsCart = ticketsCart;
    this.personID = personID;
  }

  public List<ProductsCartDTO> getProductsCart() {
    return this.productsCart;
  }

  public void setProductsCart(List<ProductsCartDTO> productsCart) {
    this.productsCart = productsCart;
  }

  public List<TicketsCartDTO> getTicketsCart() {
    return this.ticketsCart;
  }

  public void setTicketsCart(List<TicketsCartDTO> ticketsCart) {
    this.ticketsCart = ticketsCart;
  }

  public UUID getPersonID() {
    return this.personID;
  }

  public void setPersonID(UUID personID) {
    this.personID = personID;
  }

}
