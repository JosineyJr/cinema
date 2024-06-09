package com.cinema.domain.usecases.products;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.ICreateInventoryRepository;
import com.cinema.domain.contracts.repositories.products.ICreateProductRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByNameRepository;
import com.cinema.domain.entities.products.ProductInfo;
import com.cinema.domain.errors.products.ProductAlreadyExistsError;

public class CreateProductUseCase {
  private ICreateProductRepository createProductRepository;
  private IFindProductByNameRepository findProductByNameRepository;

  public CreateProductUseCase(ICreateProductRepository createProductRepository,
      ICreateInventoryRepository createInventoryRepository, IFindProductByNameRepository findProductByNameRepository) {
    this.createProductRepository = createProductRepository;
    this.findProductByNameRepository = findProductByNameRepository;
  }

  public UUID execute(String name, double price) throws ProductAlreadyExistsError {
    if (this.findProductByNameRepository.findByName(name) != null) {
      throw new ProductAlreadyExistsError();
    }

    ProductInfo product = new ProductInfo(name, price);
    return this.createProductRepository.create(product);
  }
}
