package com.cinema.domain.usecases.sale;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.IDeleteProductRepository;
import com.cinema.domain.contracts.repositories.sale.IFindProductCartByIDRepository;
import com.cinema.domain.errors.sale.ProductCartNotFoundError;

public class RemoveProductCartFromCartUseCase {
  private IFindProductCartByIDRepository findProductByIDRepository;
  private IDeleteProductRepository deleteProductRepository;

  public RemoveProductCartFromCartUseCase(
      IFindProductCartByIDRepository findProductByIDRepository,
      IDeleteProductRepository deleteProductRepository) {
    this.findProductByIDRepository = findProductByIDRepository;
    this.deleteProductRepository = deleteProductRepository;
  }

  /**
   * Executes the use case to remove a product from the cart.
   *
   * @param ID the ID of the product to be removed
   * @throws ProductCartNotFoundError if the product with the given ID is not
   *                                  found
   */
  public void execute(UUID ID) throws ProductCartNotFoundError {
    if (this.findProductByIDRepository.findProductByID(ID) == null) {
      throw new ProductCartNotFoundError();
    }

    this.deleteProductRepository.deleteProduct(ID);
  }
}
