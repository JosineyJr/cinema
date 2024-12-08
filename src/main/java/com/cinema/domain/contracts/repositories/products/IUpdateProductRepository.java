package com.cinema.domain.contracts.repositories.products;

import com.cinema.domain.entities.products.Product;

public interface IUpdateProductRepository {
  public Product updateProduct(Product product);
}
