package com.cinema.domain.contracts.repositories.products;

import java.util.UUID;

public interface IDeleteProductRepository {
  void deleteProduct(UUID ID);
}
