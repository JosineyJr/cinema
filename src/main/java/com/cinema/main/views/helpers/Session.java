package com.cinema.main.views.helpers;

/**
 * The Session class represents a user session in the cinema application.
 * It stores the user's CPF (Cadastro de Pessoa Física) and role.
 */
public class Session {
  private static String CPF = null;
  private static String role = null;

  /**
   * Retrieves the CPF (Cadastro de Pessoa Física) of the user.
   *
   * @return The CPF of the user.
   */
  public static String getCPF() {
    return CPF;
  }

  /**
   * Sets the CPF (Cadastro de Pessoa Física) of the user.
   *
   * @param CPF The CPF to set.
   */
  public static void setCPF(String CPF) {
    Session.CPF = CPF;
  }

  /**
   * Retrieves the role of the user.
   *
   * @return The role of the user.
   */
  public static String getRole() {
    return role;
  }

  /**
   * Sets the role of the user.
   *
   * @param role The role to set.
   */
  public static void setRole(String role) {
    Session.role = role;
  }
}
