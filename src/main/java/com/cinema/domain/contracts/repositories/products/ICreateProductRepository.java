package com.cinema.domain.contracts.repositories.products;

import com.cinema.domain.entities.products.Product;

public interface ICreateProductRepository {
  public void create(Product product);
}
