package com.cinema.domain.usecases.products;

import com.cinema.domain.contracts.repositories.products.IUpdateInventoryRepository;
import com.cinema.domain.entities.products.Inventory;

public class UpdateInventoryUseCase {
  private IUpdateInventoryRepository updateInventoryRepository;

  public UpdateInventoryUseCase(IUpdateInventoryRepository updateInventoryRepository) {
    this.updateInventoryRepository = updateInventoryRepository;
  }

  public void execute(Inventory inventory) {
    updateInventoryRepository.updateInventory(inventory);
  }
}
