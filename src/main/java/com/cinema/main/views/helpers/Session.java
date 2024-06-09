package com.cinema.main.views.helpers;

public class Session {
  private static String CPF = "58cc7453-e8a6-41df-9e38-6cb6270590a4";
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
