package com.aneeque.backendservice.exception;


import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handlerApiRequestException(ApiRequestException ex) {
        log.error(ex.getMessage());
        ApiException apiException = new ApiException(ex.getMessage(), ex, ex.getHttpStatus(), LocalDateTime.now());
        return new ResponseEntity<>(apiException, ex.getHttpStatus());

    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<Object> handlerBadCredentialsException(Exception ex) {
        log.error(ex.getMessage());
        ApiException apiException = new ApiException(ex.getMessage(), ex, HttpStatus.FORBIDDEN, LocalDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(value = {DisabledException.class})
    public ResponseEntity<Object> handlerDisabledException(Exception ex) {
        log.error(ex.getMessage());
        ApiException apiException = new ApiException(ex.getMessage(), ex, HttpStatus.FORBIDDEN, LocalDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<Object> handlerUsernameNotFoundException(Exception ex) {
        log.error(ex.getMessage());
        ApiException apiException = new ApiException(ex.getMessage(), ex, HttpStatus.FORBIDDEN, LocalDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.FORBIDDEN);

    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> IllegalArgumentException(Exception ex) {
        log.error(ex.getMessage());
        ApiException apiException = new ApiException(ex.getMessage(), ex, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);

    }



    @ExceptionHandler(value = {ExpiredJwtException.class})
    public ResponseEntity<Object> ExpiredJwtExceptionHandler(Exception ex) {
        log.error(ex.getMessage());
        ApiException apiException = new ApiException(ex.getMessage(), ex, HttpStatus.UNAUTHORIZED, LocalDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.UNAUTHORIZED);

    }

    @ExceptionHandler(value = {AuthenticationException.class})
    public ResponseEntity<Object> AuthenticationExceptionHandler(Exception ex) {
        log.error(ex.getMessage());
        ApiException apiException = new ApiException(ex.getMessage(), ex, HttpStatus.UNAUTHORIZED, LocalDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.UNAUTHORIZED);

    }
@ExceptionHandler(value = {NoSuchMethodError.class})
    public ResponseEntity<Object> ANoSuchMethodErrorHandler(Error ex) {
        log.error(ex.getMessage());
        ApiException apiException = new ApiException(ex.getMessage(), ex, HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    public ResponseEntity<Object> DataIntegrityViolationException(Exception ex) {
        if (ex.getMessage().contains("ConstraintViolationException")) {
            ApiException apiException = new ApiException(ex.getMessage(), ex, HttpStatus.BAD_REQUEST, LocalDateTime.now());
            return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
        }
        ApiException apiException = new ApiException(ex.getMessage(), ex, HttpStatus.FORBIDDEN, LocalDateTime.now());
        return new ResponseEntity<>(apiException, HttpStatus.FORBIDDEN);
    }


}
