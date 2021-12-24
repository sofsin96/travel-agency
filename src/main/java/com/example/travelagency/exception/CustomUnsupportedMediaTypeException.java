package com.example.travelagency.exception;

public class CustomUnsupportedMediaTypeException extends RuntimeException {

    public CustomUnsupportedMediaTypeException(String type) {
        super(String.format(
                "This media type is not accepted. Use %s in stead.", type));
    }
}
