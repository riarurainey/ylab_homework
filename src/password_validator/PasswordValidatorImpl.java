package password_validator;

public class PasswordValidatorImpl implements PasswordValidator {

    @Override
    public boolean validate(String login, String password, String confirmPassword) {
        return validateLogin(login) & validatePassword(password, confirmPassword);
    }

    private boolean validateLogin(String login) {
        try {
            if (!login.matches("^[a-zA-Z0-9_]+$")) {
                throw new WrongLoginException("Логин содержит недопустимые символы");
            } else if (login.length() > 20) {
                throw new WrongLoginException("Логин слишком длинный");
            }
        } catch (WrongLoginException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }


    private boolean validatePassword(String password, String confirmPassword) {
        try {
            if (!password.matches("^[a-zA-Z0-9_]+$")) {
                throw new WrongPasswordException("Пароль содержит недопустимые символы");
            } else if (password.length() > 20) {
                throw new WrongPasswordException("Пароль слишком длинный");
            } else if (!password.equals(confirmPassword)) {
                throw new WrongPasswordException("Пароль и подтверждение не совпадают");
            }
        } catch (WrongPasswordException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
