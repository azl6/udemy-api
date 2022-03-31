package com.udemy.gvendas.exceptions;

public class InvalidQuantityException extends RuntimeException{

    public InvalidQuantityException(String msg){
        super(msg);
    }
}
