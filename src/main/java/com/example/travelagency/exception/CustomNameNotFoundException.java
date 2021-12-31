package com.example.travelagency.exception;

public class CustomNameNotFoundException extends RuntimeException {

    public CustomNameNotFoundException(String property, String name) {
        super(String.format(
                "Request processing failed. %s : %s does not exist.", property, name));
    }
}
