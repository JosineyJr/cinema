package com.cinema.domain.helpers;

import java.time.LocalDateTime;

public class TimeIsAfter {
  public static boolean validate(LocalDateTime time) {
    return time.isAfter(LocalDateTime.now());
  }
}
