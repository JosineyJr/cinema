package com.cinema.domain.contracts.repositories.products;

import com.cinema.domain.entities.products.ProductInfos;

public interface IUpdateProductInfosRepository {
  public ProductInfos updateProduct(ProductInfos product);
}
