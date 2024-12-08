package com.cinema.domain.usecases.products;

import java.util.UUID;
import com.cinema.domain.contracts.repositories.products.IDeleteInventoryRepository;
import com.cinema.domain.contracts.repositories.products.IFindInventoryByIdRepository;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.domain.errors.products.InventoryNotFoundError;

public class DeleteInventoryUseCase {
  private IDeleteInventoryRepository deleteInventoryRepository;
  private IFindInventoryByIdRepository findInventoryByIdRepository;

  public DeleteInventoryUseCase(IDeleteInventoryRepository deleteInventoryRepository,
      IFindInventoryByIdRepository findInventoryByIdRepository) {
    this.deleteInventoryRepository = deleteInventoryRepository;
    this.findInventoryByIdRepository = findInventoryByIdRepository;
  }

  /**
   * Executes the use case to delete an inventory item by its ID.
   *
   * @param id The ID of the inventory item to be deleted.
   * @throws InventoryNotFoundError If the inventory item with the specified ID is not found.
   */
  public void execute(UUID id) throws InventoryNotFoundError {
    Inventory inventory = findInventoryByIdRepository.findById(id);

    if (inventory == null) {
      throw new InventoryNotFoundError();
    }

    deleteInventoryRepository.deleteInventory(inventory);
  }
}
