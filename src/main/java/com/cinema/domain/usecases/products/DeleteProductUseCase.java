package com.cinema.domain.usecases.products;

import java.util.UUID;
import com.cinema.domain.contracts.repositories.products.IDeleteProductRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByIDRepository;
import com.cinema.domain.errors.products.ProductNotFoundError;

public class DeleteProductUseCase {
  private IDeleteProductRepository deleteProductRepository;
  private IFindProductByIDRepository findProductByIdRepository;

  public DeleteProductUseCase(
      IDeleteProductRepository deleteProductRepository,
      IFindProductByIDRepository findProductByIdRepository) {
    this.deleteProductRepository = deleteProductRepository;
    this.findProductByIdRepository = findProductByIdRepository;
  }

  /**
   * Executes the use case to delete product information by ID.
   *
   * @param id The ID of the product to be deleted.
   * @throws ProductNotFoundErrora If the product with the given ID is not found.
   */
  public void execute(UUID id) throws ProductNotFoundError {
    if (findProductByIdRepository.findById(id) == null) {
      throw new ProductNotFoundError();
    }

    deleteProductRepository.deleteProduct(id);
  }
}
