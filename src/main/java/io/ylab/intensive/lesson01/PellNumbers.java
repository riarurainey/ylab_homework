package io.ylab.intensive.lesson01;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PellNumbers {

    public static void main(String[] args) {
        readNumber();
    }

    private static void readNumber() {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            System.out.println(getPellNumbers(n));

        } catch (InputMismatchException e) {
            System.out.println("Неверный формат ввода. Нужно ввести число.");
        }
    }

    private static int getPellNumbers(int n) {
        if (n <= 2) {
            return n;
        } else {
            return 2 * getPellNumbers(n - 1) + getPellNumbers(n - 2);
        }
    }
}

