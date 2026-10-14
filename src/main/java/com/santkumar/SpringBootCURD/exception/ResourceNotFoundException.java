package com.santkumar.SpringBootCURD.exception;

public class ResourceNotFoundException  extends  RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
