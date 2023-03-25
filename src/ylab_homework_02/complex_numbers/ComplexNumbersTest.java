package ylab_homework_02.complex_numbers;

public class ComplexNumbersTest {

    public static void main(String[] args) {
        ComplexNumber number1 = new ComplexNumber(66.8, 14.11);
        ComplexNumber number2 = new ComplexNumber(45.1, 10.33);

        System.out.println("Результат сложения двух комплексных чисел: " + ComplexNumbers.add(number1, number2));
        System.out.println("Результат вычитания двух комплексных чисел: " + ComplexNumbers.subtract(number1, number2));
        System.out.println("Результат умножения двух комплексных чисел: " + ComplexNumbers.multiply(number1, number2));
        System.out.println("Результат получения модуля комплексного числа: " + ComplexNumbers.abs(number1));

    }
}
