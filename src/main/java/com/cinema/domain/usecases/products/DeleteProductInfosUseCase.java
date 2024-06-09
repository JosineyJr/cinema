package com.cinema.domain.usecases.products;

import java.util.UUID;
import com.cinema.domain.contracts.repositories.products.IDeleteProductInfosRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductInfosByIdRepository;
import com.cinema.domain.errors.products.ProductNotFoundError;

public class DeleteProductInfosUseCase {
  private IDeleteProductInfosRepository deleteProductRepository;
  private IFindProductInfosByIdRepository findProductByIdRepository;

  public DeleteProductInfosUseCase(IDeleteProductInfosRepository deleteProductRepository,
      IFindProductInfosByIdRepository findProductByIdRepository) {
    this.deleteProductRepository = deleteProductRepository;
    this.findProductByIdRepository = findProductByIdRepository;
  }

  public void deleteProduct(UUID id) throws ProductNotFoundError {
    if (findProductByIdRepository.findById(id) == null) {
      throw new ProductNotFoundError();
    }

    deleteProductRepository.deleteProduct(id);
  }
}
