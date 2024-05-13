package com.cinema.infra.db.postgres.repositores.users;

import com.cinema.domain.contracts.repositories.users.ICreateAdminRepository;
import com.cinema.domain.entities.users.Admin;
import com.cinema.infra.db.postgres.entities.users.PgAdmin;
import com.cinema.infra.db.postgres.repositores.PgRepository;

public class PgAdminRepository extends PgRepository implements ICreateAdminRepository {

  @Override
  public void createAdmin(Admin admin) {
    this.session.persist(
        new PgAdmin(admin.getFirstName(), admin.getLastName(), admin.getCPF(), admin.getPassword()));
  }
}
