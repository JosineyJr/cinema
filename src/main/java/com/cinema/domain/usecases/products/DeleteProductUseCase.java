package com.cinema.domain.usecases.products;

import java.util.UUID;
import com.cinema.domain.contracts.repositories.products.IDeleteProductRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByIdRepository;
import com.cinema.domain.errors.products.ProductNotFoundError;

public class DeleteProductUseCase {
  private IDeleteProductRepository deleteProductRepository;
  private IFindProductByIdRepository findProductByIdRepository;

  public DeleteProductUseCase(IDeleteProductRepository deleteProductRepository, IFindProductByIdRepository findProductByIdRepository) {
    this.deleteProductRepository = deleteProductRepository;
    this.findProductByIdRepository = findProductByIdRepository;
  }

  public void deleteProduct(UUID id) throws ProductNotFoundError{
    if(findProductByIdRepository.findById(id) == null) {
      throw new ProductNotFoundError();
    }

    deleteProductRepository.deleteProduct(id);
  }
}
