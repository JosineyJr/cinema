package com.cinema.domain.usecases.products;

import java.util.List;

import com.cinema.domain.contracts.repositories.products.IListProductsInfosRepository;
import com.cinema.domain.entities.products.ProductInfos;

public class ListProductsInfosUseCase {
  private IListProductsInfosRepository listProductsInfosRepository;

  public ListProductsInfosUseCase(IListProductsInfosRepository listProductsInfosRepository) {
    this.listProductsInfosRepository = listProductsInfosRepository;
  }

  /**
   * Executes the use case to list product information.
   *
   * @return a list of ProductInfos containing the information of the products
   */
  public List<ProductInfos> execute() {
    return this.listProductsInfosRepository.listProductsInfos();
  }
}
