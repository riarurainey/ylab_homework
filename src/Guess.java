import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Guess {

    public static void main(String[] args) {
        startProgram();
    }

    private static void startProgram() {
        int secretNumber = getSecretNumber();
        int maxAttempts = 10;
        int remainsAttempts = maxAttempts;

        System.out.println("Я загадал число. У тебя " + maxAttempts + " попыток угадать.");

        while (true) {
            int inputNumber = readNumber();
            remainsAttempts--;

            if (remainsAttempts == 0) {
                System.out.println("Ты не угадал");
                break;
            } else if (inputNumber > secretNumber) {
                System.out.printf("Мое число меньше! У тебя осталось %d попыток\n", remainsAttempts);
            } else if (inputNumber < secretNumber) {
                System.out.printf("Мое число больше! У тебя осталось %d попыток\n", remainsAttempts);
            } else {
                System.out.printf("Ты угадал с %d попытки\n", maxAttempts - remainsAttempts);
                break;
            }
        }
    }

    private static int getSecretNumber() {
        return new Random().nextInt(100);
    }

    private static int readNumber() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Неверный формат ввода. Нужно ввести 1 число.");
            }
        }
    }
}