package io.ylab.intensive.lesson02.complex_numbers;

public class ComplexNumber {

    private double real;
    private double imaginary;

    public ComplexNumber(double real) {
        this(real, 0.0);
    }

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    @Override
    public String toString() {

        if (real == 0) {
            return imaginary + "i";
        }

        if (imaginary == 0) {
            return String.valueOf(real);
        }

        if (imaginary < 0) {
            return real + " - " + (-imaginary) + "i";
        }

        return real + " + " + imaginary + "i";
    }
}
