package com.cinema.domain.contracts.repositories.products;

import com.cinema.domain.entities.products.ProductInfos;

public interface IFindProductInfosByNameRepository {
  public ProductInfos findByName(String name);
}
