package io.ylab.intensive.lesson03.password_validator;

public class WrongPasswordException extends RuntimeException {

    public WrongPasswordException() {
    }

    public WrongPasswordException(String message) {
        super(message);
    }

}
