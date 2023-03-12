package ylab_homework_02.complex_numbers;

public class ComplexNumber {




    private double real;
    private double imaginary;

    public ComplexNumber(double real) {
        this(real, 0.0);
    }

    public ComplexNumber (double real, double imaginary) {
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
        return "Комплексное число: " +
                "действительная часть - " + real +
                ", мнимая часть - " + imaginary;
    }
}
