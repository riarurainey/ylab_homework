package password_validator;

public class WrongLoginException extends RuntimeException {

    public WrongLoginException() {
    }

    public WrongLoginException(final String message) {
        super(message);
    }

}
