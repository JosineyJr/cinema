package com.cinema.domain.usecases.products;

import com.cinema.domain.contracts.repositories.products.ICreateInventoryRepository;
import com.cinema.domain.contracts.repositories.products.ICreateProductRepository;
import com.cinema.domain.contracts.repositories.products.IFindProductByNameRepository;
import com.cinema.domain.entities.products.Inventory;
import com.cinema.domain.entities.products.Product;
import com.cinema.domain.errors.products.ProductAlreadyExistsError;

public class CreateProductUseCase {
  private ICreateProductRepository createProductRepository;
  private ICreateInventoryRepository createInventoryRepository;
  private IFindProductByNameRepository findProductByNameRepository;

  public CreateProductUseCase(ICreateProductRepository createProductRepository,
      ICreateInventoryRepository createInventoryRepository, IFindProductByNameRepository findProductByNameRepository) {
    this.createProductRepository = createProductRepository;
    this.createInventoryRepository = createInventoryRepository;
    this.findProductByNameRepository = findProductByNameRepository;
  }

  public void execute(String name, double price, int quantity) throws ProductAlreadyExistsError{
    if (this.findProductByNameRepository.findByName(name) != null) {
      throw new ProductAlreadyExistsError();
    }

    Product product = new Product(name, price);
    this.createProductRepository.create(product);

    Inventory inventory = new Inventory(product, quantity);
    this.createInventoryRepository.create(inventory);
  }
}
