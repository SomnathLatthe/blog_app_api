package com.somnath.blogappapis.exceptions;

public class ApiException extends RuntimeException{
    public ApiException() {
    }

    public ApiException(String message) {
        super(message);
    }
}