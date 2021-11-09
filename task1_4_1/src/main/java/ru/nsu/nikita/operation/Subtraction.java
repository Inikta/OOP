package ru.nsu.nikita.operation;

public class Subtraction extends Operation {
    public Subtraction() {
        this.unary = false;
    }

    @Override
    public double compute(double arg1, double arg2) {
        return arg1 - arg2;
    }
}
