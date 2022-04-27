package com.example.onlinehotelbookingsystem.model.binding.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MultipartFormValidator implements ConstraintValidator<ValidateMultipartForm, MultipartFile> {
    @Override
    public void initialize(ValidateMultipartForm constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {
        if (value.isEmpty()) {
            return false;
        }
        return true;
    }

//    supported types
}
