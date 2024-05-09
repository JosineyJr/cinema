package com.cinema.domain.enums.auth;

public enum Role {
  CLIENT,
  EMPLOYEE,
  ADMIN;

  @Override
  public String toString() {
    return this.name().toLowerCase();
  }
}
