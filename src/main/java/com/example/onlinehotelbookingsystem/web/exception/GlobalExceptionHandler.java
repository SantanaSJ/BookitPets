package com.example.onlinehotelbookingsystem.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView modelAndView(ObjectNotFoundException e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("not-found");
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)




}

