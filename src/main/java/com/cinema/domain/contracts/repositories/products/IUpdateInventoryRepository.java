package com.cinema.domain.contracts.repositories.products;

import com.cinema.domain.entities.products.Inventory;

public interface IUpdateInventoryRepository {
  public void updateInventory(Inventory inventory );
}
