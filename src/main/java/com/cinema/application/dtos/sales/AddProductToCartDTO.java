package com.cinema.application.dtos.sales;

public class AddProductToCartDTO {
  private String productID;
  private String personID;

  public AddProductToCartDTO(String productID, String personID) {
    this.productID = productID;
    this.personID = personID;
  }

  public String getProductID() {
    return this.productID;
  }

  public void setProductID(String productID) {
    this.productID = productID;
  }

  public String getPersonID() {
    return this.personID;
  }

  public void setPersonID(String personID) {
    this.personID = personID;
  }

}
