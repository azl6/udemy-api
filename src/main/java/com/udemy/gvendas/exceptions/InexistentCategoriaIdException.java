package com.udemy.gvendas.exceptions;

public class InexistentCategoriaIdException extends RuntimeException{

    public InexistentCategoriaIdException(String msg){
        super(msg);
    }
}
