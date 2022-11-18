package com.cepheus.sovcombank.exception;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(final String string) {
        super(string);
    }
}