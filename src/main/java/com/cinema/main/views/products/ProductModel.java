package com.cinema.main.views.products;

import com.cinema.application.dtos.products.ProductDTO;

/**
 * The ProductModel class represents a model for a product in the cinema
 * application.
 * It follows the Singleton design pattern to ensure that only one instance of
 * the class exists.
 */
public class ProductModel {
  private static ProductModel productModel = null;
  private ProductDTO product;

  private ProductModel() {
  }

  /**
   * Returns the instance of the ProductModel class.
   * If an instance does not exist, a new one is created.
   *
   * @return The instance of the ProductModel class.
   */
  public static ProductModel getInstance() {
    if (productModel == null) {
      productModel = new ProductModel();
    }
    return productModel;
  }

  /**
   * Returns the product information.
   *
   * @return The product information.
   */
  public ProductDTO getProduct() {
    return product;
  }

  /**
   * Sets the product information.
   *
   * @param product The product information to be set.
   */
  public void setProduct(ProductDTO product) {
    this.product = product;
  }
}