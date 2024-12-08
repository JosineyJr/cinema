package com.cinema.application.validation;

public class Field {
  private String value;
  private String name;

  public Field(String value, String name) {
    this.value = value;
    this.name = name;
  }


  public String getValue() {
    return this.value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
