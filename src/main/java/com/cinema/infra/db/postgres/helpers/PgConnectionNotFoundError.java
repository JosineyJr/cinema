package com.cinema.infra.db.postgres.helpers;

public class PgConnectionNotFoundError extends Exception {
  public PgConnectionNotFoundError(){
    super("No connection for postgresql was found");
  }

  @Override
  public String toString() {
    return this.getMessage();
  }
}
