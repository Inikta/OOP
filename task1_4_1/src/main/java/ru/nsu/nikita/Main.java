package ru.nsu.nikita;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        String expression = "        +5.655 +     * 6.1 2 pow 2 cos0";
        Calculator calculator = new Calculator(expression);
        double result = calculator.getResult();
        System.out.print(result);
    }
}
