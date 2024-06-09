package com.cinema.application.dtos.products;

import java.util.UUID;

public class DeleteProductDTO {
  private UUID productId;
  private UUID inventoryId;

  public DeleteProductDTO(UUID productId, UUID inventoryId) {
    this.productId = productId;
    this.inventoryId = inventoryId;
  }

  public UUID getProductId() {
    return productId;
  }

  public UUID getInventoryId() {
    return inventoryId;
  }
}
