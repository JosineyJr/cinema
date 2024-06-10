package com.cinema.domain.usecases.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.IDeleteProductRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByIDRepository;
import com.cinema.domain.errors.sale.ProductNotFoundError;

public class RemoveProductFromCartUseCase {
  private IFindProductByIDRepository findProductByIDRepository;
  private IDeleteProductRepository deleteProductRepository;

  public RemoveProductFromCartUseCase(
      IFindProductByIDRepository findProductByIDRepository,
      IDeleteProductRepository deleteProductRepository) {
    this.findProductByIDRepository = findProductByIDRepository;
    this.deleteProductRepository = deleteProductRepository;
  }

  /**
   * Executes the use case to remove a product from the cart.
   *
   * @param ID the ID of the product to be removed
   * @throws ProductNotFoundError if the product with the given ID is not found
   */
  public void execute(UUID ID) throws ProductNotFoundError {
    if(this.findProductByIDRepository.findProductByID(ID) == null){
      throw new ProductNotFoundError();
    }

    this.deleteProductRepository.deleteProduct(ID);
  }
}
