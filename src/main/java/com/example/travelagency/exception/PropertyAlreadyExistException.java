package com.example.travelagency.exception;

public class PropertyAlreadyExistException extends RuntimeException {

    public PropertyAlreadyExistException(String property) {
        super("Request processing failed. " + property + " already exists.");
    }
}
