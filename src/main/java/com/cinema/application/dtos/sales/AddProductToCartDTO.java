package com.cinema.application.dtos.sales;

public class AddProductToCartDTO {
  private String productInfoID;
  private String personID;

  public AddProductToCartDTO(String productInfoID, String personID) {
    this.productInfoID = productInfoID;
    this.personID = personID;
  }

  public String getProductInfoID() {
    return this.productInfoID;
  }

  public void setProductInfoID(String productInfoID) {
    this.productInfoID = productInfoID;
  }

  public String getPersonID() {
    return this.personID;
  }

  public void setPersonID(String personID) {
    this.personID = personID;
  }

}
