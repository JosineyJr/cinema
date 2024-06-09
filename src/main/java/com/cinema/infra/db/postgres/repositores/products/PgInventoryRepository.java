package com.cinema.infra.db.postgres.repositores.products;

import java.util.List;
import java.util.stream.Collectors;

import com.cinema.domain.contracts.repositories.products.ICreateInventoryRepository;
import com.cinema.domain.contracts.repositories.products.IListInventoryRepository;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.domain.entities.products.Product;
import com.cinema.infra.db.postgres.entities.products.PgInventory;
import com.cinema.infra.db.postgres.entities.products.PgProduct;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgInventoryRepository extends PgRepository
    implements ICreateInventoryRepository, IListInventoryRepository {
  public void create(Inventory inventory) {
    PgProduct pgProduct = new PgProduct(inventory.getProduct().getID(), inventory.getProduct().getName(),
        inventory.getProduct().getPrice());

    PgInventory pgInventory = new PgInventory(inventory.getQuantity(), pgProduct);

    this.session.persist(pgInventory);
  }

  public List<Inventory> listInventory() {
    return this.session.createQuery("FROM inventory i", PgInventory.class).getResultList().stream()
        .map(pgInventory -> new Inventory(pgInventory.getID(),
            new Product(pgInventory.getProduct().getID(), pgInventory.getProduct().getName(),
                pgInventory.getProduct().getPrice()),
            pgInventory.getQuantity()))
        .collect(Collectors.toList());
  }
}
