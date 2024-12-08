package com.cinema.domain.contracts.repositories.users;

import com.cinema.domain.entities.users.Admin;

public interface ICreateAdminRepository {
  public void createAdmin(Admin admin);
}
