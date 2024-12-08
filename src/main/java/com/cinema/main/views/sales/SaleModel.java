package com.cinema.main.views.sales;

import com.cinema.application.dtos.sales.SaleDTO;

/**
 * The SaleModel class represents the model for a sale in the cinema
 * application.
 * It is responsible for managing the sale data and providing access to it.
 */
public class SaleModel {
  private static SaleModel saleModel = null;
  private SaleDTO sale;

  private SaleModel() {
  }

  /**
   * Returns the instance of the SaleModel class.
   * If an instance does not exist, a new instance is created.
   *
   * @return the instance of the SaleModel class
   */
  public static SaleModel getInstance() {
    if (saleModel == null) {
      saleModel = new SaleModel();
    }

    return saleModel;
  }

  /**
   * Returns the sale data.
   *
   * @return the sale data
   */
  public SaleDTO getSale() {
    return sale;
  }

  /**
   * Sets the sale data.
   *
   * @param sale the sale data to be set
   */
  public void setSale(SaleDTO sale) {
    this.sale = sale;
  }
}
