package com.cinema.domain.contracts.repositories.products;

import com.cinema.domain.entities.products.Inventory;

public interface IDeleteInventoryRepository {
    void deleteInventory(Inventory inventory);
}
