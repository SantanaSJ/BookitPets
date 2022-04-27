package com.example.onlinehotelbookingsystem.model.binding.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy =  MultipartFormValidator.class)
public @interface ValidateMultipartForm {
    String message() default "File is required!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
