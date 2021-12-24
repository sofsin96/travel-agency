package com.example.travelagency.exception;

public class CustomBadRequestException extends RuntimeException {

    public CustomBadRequestException() {
        super("Your request is invalid.");
    }
}
