package com.cepheus.sovcombank.exception;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorMessage> handlerBadValidation(final ValidationException e) {
        log.error("Возникла ошибка валидации. Ошибка:" + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(e.getMessage()));
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessage> handlerNotFound(final NotFoundException e) {
        log.error("Возникла ошибка не найденного объекта. Ошибка:" + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(ConflictDataException.class)
    public ResponseEntity<ErrorMessage> handlerConflictData(final ConflictDataException e) {
        log.error("Возникла ошибка конфликтных данных. Ошибка:" + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorMessage> handlerForbidden(final ForbiddenException e) {
        log.error("Возникла ошибка запрещенных данных. Ошибка:" + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handlerArgumentNotValid(final MethodArgumentNotValidException e) {
        log.error("Возникла ошибка валидации данных. Ошибка:" + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(e.getMessage()));
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorMessage> handlerThrowableDefault(final Throwable e) {
        log.error("Возникла ошибка. Ошибка:" + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorMessage(e.getMessage()));
    }



    @ExceptionHandler(BalanceException.class)
    public ResponseEntity<ErrorMessage> handlerBalanceOfUser(final BalanceException e) {
        log.error("Возникла ошибка баланса пользователя: " + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.PAYMENT_REQUIRED)
                .body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(UserIsNotOwnerException.class)
    public ResponseEntity<ErrorMessage> handlerOwnerIsNotOwner(final UserIsNotOwnerException e) {
        log.error("Возникла ошибка доступа пользователю. Ошибка:" + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(e.getMessage()));
    }

    @ExceptionHandler(LogException.class)
    public ResponseEntity<ErrorMessage> handlerLog(final LogException e) {
        log.error("Возникла ошибка регистрации. Ошибка:" + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorMessage(e.getMessage()));
    }
}
