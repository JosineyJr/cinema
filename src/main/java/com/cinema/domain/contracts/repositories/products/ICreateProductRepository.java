package com.cinema.domain.contracts.repositories.products;

import java.util.UUID;

import com.cinema.domain.entities.products.ProductInfo;

public interface ICreateProductRepository {
  public UUID create(ProductInfo product);
}
