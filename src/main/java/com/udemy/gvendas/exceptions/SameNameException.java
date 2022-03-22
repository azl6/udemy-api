package com.udemy.gvendas.exceptions;

public class SameNameException extends RuntimeException{

    public SameNameException(String msg){
        super(msg);
    }
}
