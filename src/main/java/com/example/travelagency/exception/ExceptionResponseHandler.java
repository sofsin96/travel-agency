package com.example.travelagency.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ExceptionResponseHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ExceptionResponse exceptionResponse) {
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest req) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        body.put("errors", errors);
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler({CustomEntityNotFoundException.class})
    public ResponseEntity<Object> customEntityNotFoundException(CustomEntityNotFoundException ex, WebRequest req) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                NOT_FOUND,
                now(),
                ex.getMessage(),
                req.getDescription(true)
        );
        return buildResponseEntity(exceptionResponse);
    }

    @ExceptionHandler({CustomNameNotFoundException.class})
    public ResponseEntity<Object> customNameNotFoundException(CustomNameNotFoundException ex, WebRequest req) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                NOT_FOUND,
                now(),
                ex.getMessage(),
                req.getDescription(true)
        );
        return buildResponseEntity(exceptionResponse);
    }

    @ExceptionHandler({PropertyAlreadyExistException.class})
    public ResponseEntity<Object> propertyAlreadyExistException(PropertyAlreadyExistException ex, WebRequest req) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                NOT_FOUND,
                now(),
                ex.getMessage(),
                req.getDescription(true)
        );
        return buildResponseEntity(exceptionResponse);
    }

}
