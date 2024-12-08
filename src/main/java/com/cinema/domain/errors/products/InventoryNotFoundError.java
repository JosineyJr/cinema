package com.cinema.domain.errors.products;

public class InventoryNotFoundError extends Exception{
  public InventoryNotFoundError() {
    super("Inventário não encontrado");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
