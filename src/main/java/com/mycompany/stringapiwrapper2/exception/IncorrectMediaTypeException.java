package com.mycompany.stringapiwrapper2.exception;

public class IncorrectMediaTypeException extends RuntimeException {

    public IncorrectMediaTypeException(String mediaType, String message) {
        super(String.format("Failed for: [%s]. %s", mediaType, message));
    }
}
