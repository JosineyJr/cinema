package com.cinema.infra.providers.crypto;

import com.cinema.domain.contracts.providers.IHasher;
import com.cinema.domain.contracts.providers.IHasherComparer;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptAdapter implements IHasher,IHasherComparer {
  private int salt;

  public BCryptAdapter(int salt) {
    this.salt = salt;
  }

  @Override
  public String hash(String text) {
    return BCrypt.hashpw(text, BCrypt.gensalt(this.salt));
  }

  @Override
  public boolean compare(String text, String textToCompare) {
    return BCrypt.checkpw(text, textToCompare);
  }
}
