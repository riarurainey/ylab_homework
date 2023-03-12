package ylab_homework_02.complex_numbers;

public class ComplexNumbers {

    private ComplexNumbers() {

    }

    public static ComplexNumber add(ComplexNumber number1, ComplexNumber number2) {
        ComplexNumber complexNumber = new ComplexNumber(0.0, 0.0);
        complexNumber.setReal(number1.getReal() + number2.getReal());
        complexNumber.setImaginary(number1.getImaginary() + number2.getImaginary());
        return complexNumber;

    }

    public static ComplexNumber subtract(ComplexNumber number1, ComplexNumber number2) {
        ComplexNumber complexNumber = new ComplexNumber(0.0, 0.0);
        complexNumber.setReal(number1.getReal() - number2.getReal());
        complexNumber.setImaginary(number1.getImaginary() - number2.getImaginary());
        return complexNumber;
    }

    public static ComplexNumber multiply(ComplexNumber number1, ComplexNumber number2) {
        ComplexNumber complexNumber = new ComplexNumber(0.0, 0.0);
        complexNumber.setReal(number1.getReal() * number2.getReal());
        complexNumber.setImaginary(number1.getImaginary() * number2.getImaginary());
        return complexNumber;
    }

    public static double abs(ComplexNumber number) {
        double real = number.getReal();
        double imaginary = number.getImaginary();

        if (real != 0.0 || imaginary != 0) {
            return Math.sqrt(Math.pow(2, real) + Math.pow(2, imaginary));
        } else {
            return 0d;
        }
    }
}
