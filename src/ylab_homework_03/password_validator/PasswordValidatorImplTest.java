package ylab_homework_03.password_validator;

public class PasswordValidatorImplTest {

    public static void main(String[] args) {
        PasswordValidator passwordValidator = new PasswordValidatorImpl();
        // Невалидный логин, недопустимые символы
        System.out.println(passwordValidator.validate("login!", "password", "password"));

        // Невалидный пароль, недопустимые символы
        System.out.println(passwordValidator.validate("login", "password!", "password!"));

        // Невалидный логин, длина больше 20 символов
        System.out.println(passwordValidator.validate("123456789X123456789X1", "password", "password"));
        // Невалидный пароль, длина больше 20 символов
        System.out.println(passwordValidator.validate("login", "123456789X123456789X1", "123456789X123456789X1"));

        // Невалидный пароль, пароль и подтверждение не совпадают
        System.out.println(passwordValidator.validate("login", "password", "password!"));

    }
}
