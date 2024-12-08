package com.cinema.application.dtos.auth;

/**
 * Represents a data transfer object for login information.
 */
public class LoginDTO {
  private String CPF;
  private String password;
  private String role;
  private boolean isEmployee;

  /**
   * Constructs a new LoginDTO object with the specified CPF, password, and employee status.
   * 
   * @param CPF The CPF (Cadastro de Pessoas Físicas) of the user.
   * @param password The password of the user.
   * @param isEmployee Indicates whether the user is an employee or not.
   */
  public LoginDTO(String CPF, String password, boolean isEmployee) {
    this.CPF = CPF;
    this.password = password;
    this.isEmployee = isEmployee;
  }

  /**
   * Gets the CPF of the user.
   * 
   * @return The CPF of the user.
   */
  public String getCPF() {
    return CPF;
  }

  /**
   * Sets the CPF of the user.
   * 
   * @param CPF The CPF (Cadastro de Pessoas Físicas) of the user.
   */
  public void setCPF(String CPF) {
    this.CPF = CPF;
  }

  /**
   * Gets the password of the user.
   * 
   * @return The password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password of the user.
   * 
   * @param password The password of the user.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the role of the user.
   * 
   * @return The role of the user.
   */
  public String getRole() {
    return role;
  }

  /**
   * Sets the role of the user.
   * 
   * @param role The role of the user.
   */
  public void setRole(String role) {
    this.role = role;
  }

  /**
   * Checks if the user is an employee.
   * 
   * @return true if the user is an employee, false otherwise.
   */
  public boolean isEmployee() {
    return this.isEmployee;
  }

  /**
   * Sets the employee status of the user.
   * 
   * @param isEmployee true if the user is an employee, false otherwise.
   */
  public void setIsEmployee(boolean isEmployee) {
    this.isEmployee = isEmployee;
  }
}
