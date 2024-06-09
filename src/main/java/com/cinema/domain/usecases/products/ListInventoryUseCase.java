package com.cinema.domain.usecases.products;

import java.util.List;

import com.cinema.domain.contracts.repositories.products.IListInventoryRepository;
import com.cinema.domain.entities.products.Inventory;

public class ListInventoryUseCase {
  private IListInventoryRepository listInventoryRepository;

  public ListInventoryUseCase(IListInventoryRepository listInventoryRepository) {
    this.listInventoryRepository = listInventoryRepository;
  }

  public List<Inventory> execute() {
    return this.listInventoryRepository.listInventory();
  }
}
