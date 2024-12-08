package com.cinema.application.decorators;

import java.util.ArrayList;

import com.cinema.application.contracts.DbTransaction;
import com.cinema.application.controllers.Controller;
import com.cinema.application.helpers.Response;
import com.cinema.application.validation.IValidator;

/**
 * A decorator class that adds database transaction functionality to a
 * controller.
 *
 * @param <T> the type of object handled by the controller
 */
public class DbTransactionController<T> extends Controller<T> {
  private Controller<T> decoratee;
  private DbTransaction dbTransaction;

  /**
   * Constructs a new DbTransactionController with the specified decoratee and
   * database transaction.
   *
   * @param decoratee     the controller to decorate
   * @param dbTransaction the database transaction to use
   */
  public DbTransactionController(Controller<T> decoratee, DbTransaction dbTransaction) {
    this.decoratee = decoratee;
    this.dbTransaction = dbTransaction;
  }

  /**
   * Performs the operation on the object, handling it with the decoratee
   * controller within a database transaction.
   *
   * @param object the object to perform the operation on
   * @return the response from the decoratee controller
   * @throws Exception if an error occurs during the operation or transaction
   *                   handling
   */
  @Override
  public Response<?> perform(T object) {
    this.dbTransaction.openTransaction();

    try {
      Response<?> response = this.decoratee.handle(object);

      if (response.getStatusCode() >= 400) {
        this.dbTransaction.rollback();

        return response;
      }

      this.dbTransaction.commit();

      return response;
    } catch (Throwable e) {
      this.dbTransaction.rollback();

      throw e;
    } finally {
      this.dbTransaction.closeTransaction();
    }
  }

  /**
   * Builds and returns an empty list of validators.
   *
   * @param object the object to build validators for
   * @return an empty list of validators
   */
  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }
}
