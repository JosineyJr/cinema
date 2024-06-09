package com.cinema.application.dtos.users;

/**
 * Represents a data transfer object for creating an employee.
 */
public class CreateEmployeeDTO {
  private String firstName;
  private String lastName;
  private String CPF;
  private String password;
  private String passwordConfirmation;
  private boolean isAdmin;

  /**
   * Constructs a new CreateEmployeeDTO object with the specified parameters.
   *
   * @param firstName             the first name of the employee
   * @param lastName              the last name of the employee
   * @param CPF                   the CPF (Brazilian identification number) of the employee
   * @param password              the password of the employee
   * @param passwordConfirmation  the confirmation of the employee's password
   */
  public CreateEmployeeDTO(String firstName, String lastName, String CPF, String password,
      String passwordConfirmation) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.CPF = CPF;
    this.password = password;
    this.passwordConfirmation = passwordConfirmation;
  }

  /**
   * Gets the first name of the employee.
   *
   * @return the first name of the employee
   */
  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Sets the first name of the employee.
   *
   * @param firstName the first name of the employee
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the last name of the employee.
   *
   * @return the last name of the employee
   */
  public String getLastName() {
    return this.lastName;
  }

  /**
   * Sets the last name of the employee.
   *
   * @param lastName the last name of the employee
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the CPF (Brazilian identification number) of the employee.
   *
   * @return the CPF of the employee
   */
  public String getCPF() {
    return this.CPF;
  }

  /**
   * Sets the CPF (Brazilian identification number) of the employee.
   *
   * @param CPF the CPF of the employee
   */
  public void setCPF(String CPF) {
    this.CPF = CPF;
  }

  /**
   * Gets the password of the employee.
   *
   * @return the password of the employee
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Sets the password of the employee.
   *
   * @param password the password of the employee
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Gets the confirmation of the employee's password.
   *
   * @return the password confirmation of the employee
   */
  public String getPasswordConfirmation() {
    return this.passwordConfirmation;
  }

  /**
   * Sets the confirmation of the employee's password.
   *
   * @param passwordConfirmation the password confirmation of the employee
   */
  public void setPasswordConfirmation(String passwordConfirmation) {
    this.passwordConfirmation = passwordConfirmation;
  }

  /**
   * Checks if the employee is an admin.
   *
   * @return true if the employee is an admin, false otherwise
   */
  public boolean isAdmin() {
    return this.isAdmin;
  }

  /**
   * Sets whether the employee is an admin.
   *
   * @param isAdmin true if the employee is an admin, false otherwise
   */
  public void setIsAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
}
