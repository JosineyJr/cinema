package com.cinema.application.dtos.auth;

public class LoginDTO {
  private String CPF;
  private String password;
  private String role;
  private boolean isEmployee;

  public LoginDTO(String CPF, String password, boolean isEmployee) {
    this.CPF = CPF;
    this.password = password;
    this.isEmployee = isEmployee;
  }

  public String getCPF() {
    return CPF;
  }

  public void setCPF(String CPF) {
    this.CPF = CPF;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public boolean isEmployee() {
    return this.isEmployee;
  }

  public void setIsEmployee(boolean isEmployee) {
    this.isEmployee = isEmployee;
  }
}
