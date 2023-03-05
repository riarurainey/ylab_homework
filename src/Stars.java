import java.util.InputMismatchException;
import java.util.Scanner;

public class Stars {

    public static void main(String[] args) {
        readData();
    }

    private static void readData() {
        try (Scanner scanner = new Scanner(System.in)) {
            int rows = scanner.nextInt();
            int columns = scanner.nextInt();
            String template = scanner.next();
            printResult(rows, columns, template);
        } catch (InputMismatchException e) {
            System.out.println("Неверный формат ввода. Нужно ввести 2 числа и 1 символ.");
        }
    }

    private static void printResult(int rows, int columns, String template) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(template + " ");
            }
            System.out.println();
        }

    }
}
