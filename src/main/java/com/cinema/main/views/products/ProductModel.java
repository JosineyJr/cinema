package com.cinema.main.views.products;

import com.cinema.application.dtos.products.ProductInfosDTO;

public class ProductModel {
  private static ProductModel productModel = null;
  private ProductInfosDTO product;

  private ProductModel() {}

  public static ProductModel getInstance() {
      if (productModel == null) {
          productModel = new ProductModel();
      }
      return productModel;
  }

  public ProductInfosDTO getProduct() {
      return product;
  }

  public void setProduct(ProductInfosDTO product) {
      this.product = product;
  }
}