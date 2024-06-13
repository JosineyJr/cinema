package com.cinema.domain.contracts.repositories.sale;

import java.util.UUID;

import com.cinema.domain.entities.sale.ProductCart;

public interface IFindProductCartByIDRepository {
  ProductCart findProductByID(UUID ID);
}
