package com.cinema.domain.contracts.repositories.products;

import java.util.List;

import com.cinema.domain.entities.products.Product;

public interface IListProductsRepository {
  public List<Product> listProducts();
}
