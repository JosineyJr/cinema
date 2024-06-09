package com.cinema.main.factories.db;

import com.cinema.infra.db.postgres.helpers.PgConnection;

public class PgConnectionFactory {
  /**
   * Creates a new instance of PgConnection.
   * 
   * @return The PgConnection instance.
   */
  public static PgConnection make() {
    return PgConnection.getInstance();
  }
}
