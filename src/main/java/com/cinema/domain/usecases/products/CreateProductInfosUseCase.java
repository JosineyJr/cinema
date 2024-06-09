package com.cinema.domain.usecases.products;

import java.util.UUID;

import com.cinema.domain.contracts.repositories.products.ICreateInventoryRepository;
import com.cinema.domain.contracts.repositories.products.ICreateProductInfosRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductInfosByNameRepository;
import com.cinema.domain.entities.products.ProductInfos;
import com.cinema.domain.errors.products.ProductAlreadyExistsError;

public class CreateProductInfosUseCase {
  private ICreateProductInfosRepository createProductInfosRepository;
  private IFindProductInfosByNameRepository findProductInfosByNameRepository;

  public CreateProductInfosUseCase(ICreateProductInfosRepository createProductInfosRepository,
      ICreateInventoryRepository createInventoryRepository,
      IFindProductInfosByNameRepository findProductInfosByNameRepository) {
    this.createProductInfosRepository = createProductInfosRepository;
    this.findProductInfosByNameRepository = findProductInfosByNameRepository;
  }

  public UUID execute(String name, double price) throws ProductAlreadyExistsError {
    if (this.findProductInfosByNameRepository.findByName(name) != null) {
      throw new ProductAlreadyExistsError();
    }

    ProductInfos product = new ProductInfos(name, price);
    return this.createProductInfosRepository.create(product);
  }
}
