package com.cinema.domain.contracts.repositories.products;

import java.util.UUID;

import com.cinema.domain.entities.products.Product;

public interface ICreateProductRepository {
  public UUID create(Product product);
}
