package com.cinema.domain.contracts.repositories.products;

import java.util.UUID;

import com.cinema.domain.entities.products.Inventory;

public interface IFindInventoryByProductIDRepository {
  public Inventory findInventoryByProductID(UUID productID);
}
