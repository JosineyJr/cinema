package com.cinema.infra.db.postgres.repositores.products;

import com.cinema.domain.contracts.repositories.products.ICreateInventoryRepository;
import com.cinema.domain.contracts.repositories.products.IDeleteInventoryRepository;
import com.cinema.domain.contracts.repositories.products.IFindInventoryByIdRepository;
import com.cinema.domain.contracts.repositories.products.IFindInventoryByProductInfosIDRepository;
import com.cinema.domain.contracts.repositories.products.IListInventoryRepository;
import com.cinema.domain.contracts.repositories.products.IUpdateInventoryRepository;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.domain.entities.products.ProductInfos;
import com.cinema.infra.db.postgres.entities.products.PgInventory;
import com.cinema.infra.db.postgres.entities.products.PgProductInfos;
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
    IUpdateInventoryRepository, IFindInventoryByProductInfosIDRepository {

  public void create(Inventory inventory) {
    PgProductInfos pgProduct = this.session.get(PgProductInfos.class, inventory.getProductInfos().getID());

    PgInventory pgInventory = new PgInventory(
        inventory.getQuantity(),
        pgProduct);

    this.session.persist(pgInventory);
  }

  public List<Inventory> listInventory() {
    return this.session.createQuery("FROM inventory i", PgInventory.class)
        .getResultList()
        .stream()
        .map(
            pgInventory -> new Inventory(
                pgInventory.getID(),
                new ProductInfos(
                    pgInventory.getProductInfos().getID(),
                    pgInventory.getProductInfos().getName(),
                    pgInventory.getProductInfos().getPrice()),
                pgInventory.getQuantity()))
        .collect(Collectors.toList());
  }

  public void deleteInventory(Inventory inventory) {
    PgInventory pgInventory = this.session.get(PgInventory.class, inventory.getID());

    this.session.remove(pgInventory);
  }

  public Inventory findById(UUID id) {
    PgInventory pgInventory = this.session.get(PgInventory.class, id);

    return new Inventory(
        pgInventory.getID(),
        new ProductInfos(
            pgInventory.getProductInfos().getID(),
            pgInventory.getProductInfos().getName(),
            pgInventory.getProductInfos().getPrice()),
        pgInventory.getQuantity());
  }

  public void updateInventory(Inventory inventory) {
    PgInventory pgInventory = this.session.get(PgInventory.class, inventory.getID());

    pgInventory.setQuantity(inventory.getQuantity());

    this.session.persist(pgInventory);
  }

  @Override
  public Inventory findInventoryByProductInfosID(UUID productInfosID) {
    PgInventory inventory = this.session
        .createQuery("FROM inventory WHERE productInfos.id = :productInfosID", PgInventory.class)
        .setParameter("productInfosID", productInfosID)
        .getSingleResult();

    return ConvertEntities.convertInventory(inventory);
  }
}
