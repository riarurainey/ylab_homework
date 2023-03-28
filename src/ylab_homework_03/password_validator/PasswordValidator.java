package ylab_homework_03.password_validator;

public interface PasswordValidator {

    boolean validate(String login, String password, String confirmPassword);
}
