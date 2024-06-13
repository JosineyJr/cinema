package com.cinema.domain.usecases.products;

import com.cinema.domain.contracts.repositories.products.IUpdateProductRepository;
import com.cinema.domain.entities.products.Product;

public class UpdateProductUseCase {
  private IUpdateProductRepository updateProductRepository;

  public UpdateProductUseCase(IUpdateProductRepository updateProductRepository) {
    this.updateProductRepository = updateProductRepository;
  }

  /**
   * Executes the use case to update the product information.
   *
   * @param product The updated product information.
   * @return The updated product information.
   */
  public Product execute(Product product) {
    return updateProductRepository.updateProduct(product);
  }
}
