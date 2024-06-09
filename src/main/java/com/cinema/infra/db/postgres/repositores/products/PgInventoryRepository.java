package com.cinema.infra.db.postgres.repositores.products;

import com.cinema.domain.contracts.repositories.products.ICreateInventoryRepository;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.infra.db.postgres.entities.products.PgInventory;
import com.cinema.infra.db.postgres.entities.products.PgProductInfos;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgInventoryRepository extends PgRepository implements ICreateInventoryRepository {
  public void create(Inventory inventory) {
    PgProductInfos pgProduct = new PgProductInfos(inventory.getProduct().getID(), inventory.getProduct().getName(),
        inventory.getProduct().getPrice());

    PgInventory pgInventory = new PgInventory(inventory.getQuantity(), pgProduct);

    this.session.persist(pgInventory);
  }
}
