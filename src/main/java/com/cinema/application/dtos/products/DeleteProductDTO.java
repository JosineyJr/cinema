package com.cinema.application.dtos.products;

import java.util.UUID;

/**
 * Represents a data transfer object for deleting product information.
 */
public class DeleteProductDTO {
  private UUID productId;
  private UUID inventoryId;

  /**
   * Constructs a new DeleteProductDTO object with the specified product and
   * inventory IDs.
   *
   * @param productId   The ID of the product to be deleted.
   * @param inventoryId The ID of the inventory associated with the product.
   */
  public DeleteProductDTO(UUID productId, UUID inventoryId) {
    this.productId = productId;
    this.inventoryId = inventoryId;
  }

  /**
   * Gets the ID of the product to be deleted.
   *
   * @return The product ID.
   */
  public UUID getProductId() {
    return productId;
  }

  /**
   * Gets the ID of the inventory associated with the product.
   *
   * @return The inventory ID.
   */
  public UUID getInventoryId() {
    return inventoryId;
  }
}
