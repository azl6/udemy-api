package com.udemy.gvendas.controllers;

import com.udemy.gvendas.exceptions.StandardError;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(){
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), "Não encontrado", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validatorException(){
        StandardError err = new StandardError(HttpStatus.NOT_ACCEPTABLE.value(), "O nome da categoria deve ter entre 3 e 50 caracteres", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }
}
