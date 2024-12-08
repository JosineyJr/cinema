package com.cinema.domain.contracts.repositories.sale;

import java.util.UUID;

import com.cinema.domain.entities.sale.ProductSale;

public interface ICreateProductSaleRepository {
  public UUID create(ProductSale productSale);
}
