package com.cinema.domain.usecases.products;

import java.util.List;

import com.cinema.domain.contracts.repositories.products.IListProductsRepository;
import com.cinema.domain.entities.products.Product;

public class ListProductsUseCase {
  private IListProductsRepository listProductsRepository;

  public ListProductsUseCase(IListProductsRepository listProductsRepository) {
    this.listProductsRepository = listProductsRepository;
  }

  /**
   * Executes the use case to list product information.
   *
   * @return a list of Product containing the information of the products
   */
  public List<Product> execute() {
    return this.listProductsRepository.listProducts();
  }
}
