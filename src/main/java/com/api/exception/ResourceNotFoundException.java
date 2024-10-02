package com.api.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String msg) {
        super(msg);// super keyword will call the constructor of parent class.
        // if you remove super() keyword .it will not return any message in postman.
    }
}
