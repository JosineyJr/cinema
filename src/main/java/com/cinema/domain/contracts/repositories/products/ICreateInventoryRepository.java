package com.cinema.domain.contracts.repositories.products;

import com.cinema.domain.entities.products.Inventory;

public interface ICreateInventoryRepository {
  public void create(Inventory inventory);
}
