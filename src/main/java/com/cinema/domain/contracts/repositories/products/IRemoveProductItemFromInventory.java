package com.cinema.domain.contracts.repositories.products;

import java.util.UUID;

public interface IRemoveProductItemFromInventory {
  public void removeProductItemFromInventory(UUID productID, int quantity);
}
