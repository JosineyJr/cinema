package com.cinema.domain.contracts.repositories.products;

import java.util.List;

import com.cinema.domain.entities.products.ProductInfos;

public interface IListProductsInfosRepository {
  public List<ProductInfos> listProductsInfos();
}
