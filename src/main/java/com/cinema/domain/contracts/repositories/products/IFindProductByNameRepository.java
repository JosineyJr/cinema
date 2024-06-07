package com.cinema.domain.contracts.repositories.products;

import com.cinema.domain.entities.products.Product;

public interface IFindProductByNameRepository {
  public Product findByName(String name);
}
