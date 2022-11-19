package com.cepheus.sovcombank.exception;

public class UserIsNotOwnerException extends RuntimeException{
    public UserIsNotOwnerException(final String message){
        super(message);
    }
}
