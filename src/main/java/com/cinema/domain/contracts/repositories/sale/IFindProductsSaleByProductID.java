package com.cinema.domain.contracts.repositories.sale;

import java.util.List;
import java.util.UUID;

import com.cinema.domain.entities.sale.ProductSale;

public interface IFindProductsSaleByProductID {
  public List<ProductSale> findProductsSaleByProductID(UUID productID);
}
