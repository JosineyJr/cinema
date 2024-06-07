package com.cinema.infra.db.postgres.repositores.products;

import com.cinema.domain.contracts.repositories.products.ICreateInventoryRepository;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgInventoryRepository extends PgRepository implements ICreateInventoryRepository{
  public void create(Inventory inventory) {
    return;
  }  
}
