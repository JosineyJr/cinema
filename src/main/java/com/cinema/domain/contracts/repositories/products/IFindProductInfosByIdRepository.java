package com.cinema.domain.contracts.repositories.products;

import java.util.UUID;

import com.cinema.domain.entities.products.ProductInfos;

public interface IFindProductInfosByIdRepository {
  public ProductInfos findById(UUID id);
}
