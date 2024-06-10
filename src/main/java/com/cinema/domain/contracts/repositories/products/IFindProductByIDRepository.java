package com.cinema.domain.contracts.repositories.products;

import java.util.UUID;

import com.cinema.domain.entities.products.Product;

public interface IFindProductByIDRepository {
  Product findProductByID(UUID ID);
}
