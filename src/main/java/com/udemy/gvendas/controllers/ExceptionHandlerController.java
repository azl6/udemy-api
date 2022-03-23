package com.udemy.gvendas.controllers;

import com.udemy.gvendas.exceptions.*;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(NotFoundException e){
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validatorException(){
        StandardError err = new StandardError(HttpStatus.NOT_ACCEPTABLE.value(), "O nome da categoria deve ter entre 3 e 50 caracteres", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }

    @ExceptionHandler(SameNameException.class)
    public ResponseEntity<StandardError> sameName(SameNameException e){
        StandardError err = new StandardError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrity(){
        StandardError err = new StandardError(HttpStatus.NOT_ACCEPTABLE.value(), "Recurso não encontrado", System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }

    @ExceptionHandler(CategoriaNullException.class)
    public ResponseEntity<StandardError> categoriaNull(CategoriaNullException e){
        StandardError err = new StandardError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }

    @ExceptionHandler(ProdutoDuplicadoException.class)
    public ResponseEntity<StandardError> produtoDuplicado(ProdutoDuplicadoException e){
        StandardError err = new StandardError(HttpStatus.NOT_ACCEPTABLE.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(err);
    }
}
