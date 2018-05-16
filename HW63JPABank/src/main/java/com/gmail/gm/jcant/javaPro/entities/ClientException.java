package com.gmail.gm.jcant.javaPro.entities;

public class ClientException extends Exception {

    public ClientException() {
    }

    public ClientException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "AccountException: " + super.getMessage();
    }
}
