package com.cinema.main.views.helpers;

public class Session {
  private static String CPF = null;
  private static String role = null;

  public static String getCPF() {
    return CPF;
  }

  public static void setCPF(String CPF) {
    Session.CPF = CPF;
  }

  public static String getRole() {
    return role;
  }

  public static void setRole(String role) {
    Session.role = role;
  }
}
