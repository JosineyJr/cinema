package com.cinema.application.dtos.users;

public class CreateEmployeeDTO {
  private String firstName;
  private String lastName;
  private String CPF;
  private String password;
  private String passwordConfirmation;
  private boolean isAdmin;

  public CreateEmployeeDTO(String firstName, String lastName, String CPF, String password,
      String passwordConfirmation) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.CPF = CPF;
    this.password = password;
    this.passwordConfirmation = passwordConfirmation;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCPF() {
    return this.CPF;
  }

  public void setCPF(String CPF) {
    this.CPF = CPF;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPasswordConfirmation() {
    return this.passwordConfirmation;
  }

  public void setPasswordConfirmation(String passwordConfirmation) {
    this.passwordConfirmation = passwordConfirmation;
  }

  public boolean isAdmin() {
    return this.isAdmin;
  }

  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
}
