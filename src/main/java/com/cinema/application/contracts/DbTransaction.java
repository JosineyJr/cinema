package com.cinema.application.contracts;

public interface DbTransaction {
  public void openTransaction();
  public void closeTransaction();
  public void commit();
  public void rollback();
}
