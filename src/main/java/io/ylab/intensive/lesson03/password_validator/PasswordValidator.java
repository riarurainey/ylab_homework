package io.ylab.intensive.lesson03.password_validator;

public interface PasswordValidator {

    boolean validate(String login, String password, String confirmPassword);
}
