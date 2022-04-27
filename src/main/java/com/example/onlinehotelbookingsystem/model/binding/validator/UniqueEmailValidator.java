package com.example.onlinehotelbookingsystem.model.binding.validator;

import com.example.onlinehotelbookingsystem.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  private final UserService userService;

  public UniqueEmailValidator(UserService userService) {
    this.userService = userService;
  }

  @Override
  public boolean isValid(String email, ConstraintValidatorContext context) {
    if (email == null) {

      return true;
    }
    return this.userService.isEmailFree(email);
  }
}
