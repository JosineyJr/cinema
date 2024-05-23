package com.cinema.application.decorators;

import java.util.ArrayList;

import com.cinema.application.contracts.DbTransaction;
import com.cinema.application.controllers.Controller;
import com.cinema.application.helpers.Response;
import com.cinema.application.validation.IValidator;

public class DbTransactionController<T> extends Controller<T> {
  private Controller<T> decoratee;
  private DbTransaction dbTransaction;

  public DbTransactionController(Controller<T> decoratee, DbTransaction dbTransaction) {
    this.decoratee = decoratee;
    this.dbTransaction = dbTransaction;
  }

  @Override
  public Response<?> perform(T object) {
    this.dbTransaction.openTransaction();

    try {
      Response<?> response = this.decoratee.handle(object);

      this.dbTransaction.commit();

      return response;
    } catch (Exception e) {
      this.dbTransaction.rollback();

      throw e;
    } finally {
      this.dbTransaction.closeTransaction();
    }
  }

  @Override
  public ArrayList<IValidator> buildValidators(Object object) {
    return new ArrayList<IValidator>();
  }

}
