package com.gmail.gm.jcant.javaPro.entities;

public class AccountException extends Exception {

    public AccountException() {
    }

    public AccountException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "AccountException: " + super.getMessage();
    }
}
