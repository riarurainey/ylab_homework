package ylab_homework_02.complex_numbers;

public class ComplexNumbersTest {
    public static void main(String[] args) {
        ComplexNumber number1 = new ComplexNumber(66.8, 14.11);
        ComplexNumber number2 = new ComplexNumber(45.1, 10.33);
        double result = ComplexNumbers.abs(number1);
        System.out.println(result);



    }
}
