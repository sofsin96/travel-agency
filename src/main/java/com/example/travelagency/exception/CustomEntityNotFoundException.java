package com.example.travelagency.exception;

public class CustomEntityNotFoundException extends RuntimeException {

    public CustomEntityNotFoundException(String property, Long value) {
        super(String.format(
                "Request processing failed. Entity %s with ID %s does not exist.",
                property, value));
    }
}
