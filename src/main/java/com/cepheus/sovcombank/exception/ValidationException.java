package com.cepheus.sovcombank.exception;


public class ValidationException extends RuntimeException {
    public ValidationException(final String string) {
        super(string);
    }
}