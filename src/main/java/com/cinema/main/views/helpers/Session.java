package com.cinema.main.views.helpers;

import java.util.UUID;

/**
 * The Session class represents a user session in the cinema application.
 * It stores the user's CPF (Cadastro de Pessoa Física) and role.
 */
public class Session {
  private static String CPF;
  private static UUID PERSON_ID;
  private static String role;
  private static String name;

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

  /**
   * Retrieves the ID of the person.
   *
   * @return The ID of the person.
   */
  public static UUID getPersonId() {
    return PERSON_ID;
  }

  /**
   * Sets the ID of the person.
   *
   * @param personId The ID of the person to set.
   */
  public static void setPersonId(UUID personId) {
    PERSON_ID = personId;
  }

  /**
   * Retrieves the name of the user.
   *
   * @return The name of the user.
   */
  public static String getName() {
    return name;
  }

  /**
   * Sets the name of the user.
   *
   * @param name The name to set.
   */
  public static void setName(String name) {
    Session.name = name;
  }
}
