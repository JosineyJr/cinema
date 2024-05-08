package com.cinema.infra.db.crypto;

import com.cinema.domain.contracts.providers.IHasher;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptAdapter implements IHasher {
  private int salt;

  public BCryptAdapter(int salt) {
    this.salt = salt;
  }

  @Override
  public String hash(String text) {
    return BCrypt.hashpw(text, BCrypt.gensalt(this.salt));
  }
}
