package com.udemy.gvendas.controllers;

import com.udemy.gvendas.exceptions.*;
import io.jaegertracing.internal.utils.Http;
import org.hibernate.ObjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(NotFoundException e){
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<StandardError>> validatorException(MethodArgumentNotValidException e){

        List<StandardError> listaErros = gerarListaDeErros(e.getBindingResult());

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(listaErros);
    }

    private List<StandardError> gerarListaDeErros(BindingResult bindingResult){
    List<StandardError> erros = new ArrayList<>();

   bindingResult.getFieldErrors().forEach(fieldError -> {
       String msg = fieldError.getDefaultMessage();
       erros.add(new StandardError(HttpStatus.BAD_REQUEST.value(), msg, System.currentTimeMillis()));
   });

    return erros;
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
