package com.cinema.domain.contracts.repositories.products;

import java.util.UUID;

import com.cinema.domain.entities.products.Inventory;

public interface IFindInventoryByIdRepository {
  public Inventory findById(UUID id);
}
