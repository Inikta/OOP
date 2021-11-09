package ru.nsu.nikita.operation;

public class Exponentiation extends Operation {
    @Override
    public double compute(double arg1, double arg2) {
        return Math.pow(arg1, arg2);
    }
}
