package com.cinema.application.contracts;

/**
 * The DbTransaction interface represents a database transaction.
 * It provides methods to open, close, commit, and rollback a transaction.
 */
public interface DbTransaction {
  
  /**
   * Opens a new database transaction.
   */
  public void openTransaction();
  
  /**
   * Closes the current database transaction.
   */
  public void closeTransaction();
  
  /**
   * Commits the changes made in the current transaction to the database.
   */
  public void commit();
  
  /**
   * Rolls back the changes made in the current transaction.
   */
  public void rollback();
}
