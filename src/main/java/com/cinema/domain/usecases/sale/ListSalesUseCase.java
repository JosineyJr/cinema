package com.cinema.domain.usecases.sale;

import java.util.List;

import com.cinema.domain.contracts.repositories.sale.IListSalesRepository;
import com.cinema.domain.entities.sale.Sale;

public class ListSalesUseCase {
  private IListSalesRepository saleRepository;

  public ListSalesUseCase(IListSalesRepository saleRepository) {
    this.saleRepository = saleRepository;
  }

  /**
    * Executes the use case to list all sales.
    *
    * @return a list of Sale objects representing the sales.
    */
  public List<Sale> execute() {
    return this.saleRepository.listSales();
  }
}
