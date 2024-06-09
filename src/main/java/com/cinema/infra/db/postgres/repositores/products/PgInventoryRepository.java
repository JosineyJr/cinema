package com.cinema.infra.db.postgres.repositores.products;

import com.cinema.domain.contracts.repositories.products.ICreateInventoryRepository;
import com.cinema.domain.contracts.repositories.products.IDeleteInventoryRepository;
import com.cinema.domain.contracts.repositories.products.IFindInventoryByIdRepository;
import com.cinema.domain.contracts.repositories.products.IListInventoryRepository;
import com.cinema.domain.contracts.repositories.products.IUpdateInventoryRepository;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.infra.db.postgres.entities.products.PgInventory;
import com.cinema.infra.db.postgres.helpers.ConvertEntities;
import com.cinema.infra.db.postgres.repositores.PgRepository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PgInventoryRepository
    extends PgRepository
    implements
    ICreateInventoryRepository,
    IListInventoryRepository,
    IDeleteInventoryRepository,
    IFindInventoryByIdRepository,
    IUpdateInventoryRepository {

  public void create(Inventory inventory) {
    PgInventory pgInventory = ConvertEntities.pgConvertInventory(inventory);

    this.session.persist(pgInventory);
  }

  public List<Inventory> listInventory() {
    return this.session.createQuery("FROM inventory i", PgInventory.class)
        .getResultList()
        .stream()
        .map(
            pgInventory -> ConvertEntities.convertInventory(pgInventory))
        .collect(Collectors.toList());
  }

  public void deleteInventory(Inventory inventory) {
    PgInventory pgInventory = ConvertEntities.pgConvertInventory(inventory);

    this.session.remove(pgInventory);
  }

  public Inventory findById(UUID id) {
    PgInventory pgInventory = this.session.get(PgInventory.class, id);

    return ConvertEntities.convertInventory(pgInventory);
  }

  public void updateInventory(Inventory inventory) {
    PgInventory pgInventory = this.session.get(PgInventory.class, inventory.getID());

    pgInventory.setQuantity(inventory.getQuantity());

    this.session.persist(pgInventory);
  }
}
