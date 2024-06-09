package com.cinema.domain.usecases.products;

import com.cinema.domain.contracts.repositories.products.IUpdateProductInfosRepository;
import com.cinema.domain.entities.products.ProductInfos;

public class UpdateProductInfosUseCase {
  private IUpdateProductInfosRepository updateProductInfosRepository;

  public UpdateProductInfosUseCase(IUpdateProductInfosRepository updateProductInfosRepository) {
    this.updateProductInfosRepository = updateProductInfosRepository;
  }

  public ProductInfos execute(ProductInfos productInfos) {
    return updateProductInfosRepository.updateProduct(productInfos);
  }
}
