package com.cinema.domain.usecases.products;

import java.util.List;

import com.cinema.domain.contracts.repositories.products.IListProductsRepository;
import com.cinema.domain.entities.products.Product;

public class ListProductsUseCase {
  private IListProductsRepository listProductsRepository;

  public ListProductsUseCase(IListProductsRepository listProductsRepository) {
    this.listProductsRepository = listProductsRepository;
  }

  public List<Product> execute() {
    return this.listProductsRepository.listProducts();
  }
}
