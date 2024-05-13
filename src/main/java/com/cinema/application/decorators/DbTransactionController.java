package com.cinema.application.decorators;

import java.util.ArrayList;

import com.cinema.application.contracts.DbTransaction;
import com.cinema.application.controllers.Controller;
import com.cinema.application.helpers.Response;
import com.cinema.application.validation.IValidator;

public class DbTransactionController extends Controller {
  private Controller decoratee;
  private DbTransaction dbTransaction;

  public DbTransactionController(Controller decoratee, DbTransaction dbTransaction) {
    this.decoratee = decoratee;
    this.dbTransaction = dbTransaction;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Response perform(Object object) {
    this.dbTransaction.openTransaction();

    try {
      Response response = this.decoratee.handle(object);

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
