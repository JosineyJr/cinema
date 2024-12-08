package com.cinema.domain.contracts.repositories.products;

import java.util.List;

import com.cinema.domain.entities.products.Inventory;

public interface IListInventoryRepository {
  public List<Inventory> listInventory();
}
