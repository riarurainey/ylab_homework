package ylab_homework_02.snils_validator;

public class SnilsValidatorImpl implements SnilsValidator {

    @Override
    public boolean validate(String snils) {
        boolean isValid = false;

        if (snils.isBlank()) {
            System.out.println("СНИЛС не может быть пустым.");
        } else if (snils.length() != 11) {
            System.out.println("СНИЛС должен состоять из 11 цифр.");
        } else if (snils.matches("[^0-9]")) {
            System.out.println("СНИЛС может состоять только из цифр.");
        } else {
            int sum = 0;
            for (var i = 0; i < 9; i++) {
                sum += Character.getNumericValue(snils.charAt(i)) * (9 - i);
            }

            int checkDigit = 0;
            if (sum < 100) {
                checkDigit = sum;
            } else if (sum > 101) {
                checkDigit = sum % 101;
                if (checkDigit == 100) {
                    checkDigit = 0;
                }
            }

            if (checkDigit == Integer.parseInt(snils.substring(9, 11))) {
                isValid = true;
            } else {
                System.out.println("Контрольное число неверно");
            }
        }
        return isValid;
    }
}
