package ru.nsu.nikita.operation;

public class Operations {

    public static final String[] unary = {"sin", "cos", "sqrt", "log", "deg"};
    public static final String[] binary = {"pow", "+", "-", "*", "/" };

    public static double addition(double a, double b) {
        return a + b;
    }
    public static double subtraction(double a, double b) {
        return a - b;
    }
    public static double production(double a, double b) {
        return a * b;
    }
    public static double division(double a, double b) {
        return a / b;
    }
    public static double power(double a, double b) {
        return Math.pow(a, b);
    }
    public static double convertDegToDouble(double a) {
        return a / 180 * Math.PI;
    }

    public static double sinus(double a) {
        return Math.sin(a);
    }
    public static double cosine(double a) {
        return Math.cos(a);
    }
    public static double naturalLog(double a) {
        return Math.log(a);
    }
    public static double sqrt(double a) {
        return Math.sqrt(a);
    }
}
