package com.example.travelagency.exception;


public class CustomNotFoundException extends RuntimeException {

    public CustomNotFoundException(String property, String value) {
        super(String.format(
                "Resource with property %s and value %s does not exists.",
                property, value));
    }
}
