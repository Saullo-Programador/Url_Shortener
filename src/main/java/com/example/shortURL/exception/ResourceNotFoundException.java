package com.example.shortURL.exception;

public class ResourceNotFoundException extends  RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
