package com.cepheus.sovcombank.exception;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(final String string) {
        super(string);
    }
}
