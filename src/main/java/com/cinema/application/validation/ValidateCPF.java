package com.cinema.application.validation;

import com.cinema.application.errors.CPFAllDigitsSameError;
import com.cinema.application.errors.CPFMinMaxSizeError;
import com.cinema.application.errors.InvalidCPFError;

public class ValidateCPF implements IValidator {
  private Field field;

  public ValidateCPF(Field field) {
    this.field = field;
  }

  @Override
  public Exception validate() {
    // Get the CPF value
    String CPF = this.field.getValue();

    // Remove any non-digit characters from the CPF
    String cleanedCPF = CPF.replaceAll("\\D", "");

    // Check if the CPF has 11 digits
    if (cleanedCPF.length() != 11) {
      return new CPFMinMaxSizeError();
    }

    // Check if all digits are the same
    if (cleanedCPF.matches("(\\d)\\1{10}")) {
      return new CPFAllDigitsSameError();
    }

    // Calculate the first verification digit
    int sum = 0;
    for (int i = 0; i < 9; i++) {
      sum += Character.getNumericValue(cleanedCPF.charAt(i)) * (10 - i);
    }
    int firstDigit = (sum * 10) % 11;
    if (firstDigit == 10) {
      firstDigit = 0;
    }

    // Calculate the second verification digit
    sum = 0;
    for (int i = 0; i < 10; i++) {
      sum += Character.getNumericValue(cleanedCPF.charAt(i)) * (11 - i);
    }
    int secondDigit = (sum * 10) % 11;
    if (secondDigit == 10) {
      secondDigit = 0;
    }

    // Check if the verification digits match the CPF
    if (Character.getNumericValue(cleanedCPF.charAt(9)) != firstDigit ||
        Character.getNumericValue(cleanedCPF.charAt(10)) != secondDigit) {
      return new InvalidCPFError();
    }

    // CPF is valid
    return null;
  }

}
