package com.cinema.domain.contracts.repositories.sale;

import java.util.UUID;

import com.cinema.domain.entities.sale.Sale;

public interface ICreateSaleRepository {
  public UUID createSale(Sale sale);
}
