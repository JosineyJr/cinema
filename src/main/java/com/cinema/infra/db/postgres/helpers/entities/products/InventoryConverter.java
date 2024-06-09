package com.cinema.infra.db.postgres.helpers.entities.products;

import com.cinema.domain.entities.products.Inventory;
import com.cinema.infra.db.postgres.entities.products.PgInventory;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class InventoryConverter implements IEntityConverter<PgInventory, Inventory> {
  private ProductInfosConverter productInfosConverter = new ProductInfosConverter();

  @Override
  public Inventory convert(PgInventory source) {
    return new Inventory(source.getID(), productInfosConverter.convert(source.getProductInfos()), source.getQuantity());
  }

  @Override
  public PgInventory pgConverter(Inventory target) {
    return new PgInventory(target.getID(), target.getQuantity(),
        productInfosConverter.pgConverter(target.getProductInfos()));
  }
}
