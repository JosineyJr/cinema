package com.cinema.infra.db.postgres.helpers.entities.users;

import com.cinema.domain.entities.users.Admin;
import com.cinema.infra.db.postgres.entities.users.PgAdmin;
import com.cinema.infra.db.postgres.helpers.entities.IEntityConverter;

public class AdminConverter implements IEntityConverter<PgAdmin, Admin> {
  @Override
  public Admin convert(PgAdmin admin) {
    return new Admin(
        admin.getID(),
        admin.getFirstName(),
        admin.getLastName(),
        admin.getCPF(),
        admin.getPassword());
  }

  @Override
  public PgAdmin pgConverter(Admin target) {
    return new PgAdmin(
        target.getID(),
        target.getFirstName(),
        target.getLastName(),
        target.getCPF(),
        target.getPassword());
  }
}
