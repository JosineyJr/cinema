package com.cinema.main.factories.db;

import com.cinema.infra.db.postgres.helpers.PgConnection;

public class PgConnectionFactory {
  public static PgConnection make() {
    return PgConnection.getInstance();
  }
}
