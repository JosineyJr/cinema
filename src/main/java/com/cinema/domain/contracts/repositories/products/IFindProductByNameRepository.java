package com.cinema.domain.contracts.repositories.products;

import com.cinema.domain.entities.products.ProductInfo;

public interface IFindProductByNameRepository {
  public ProductInfo findByName(String name);
}
