package ru.nsu.nikita.operation;

public class Production extends Operation {
    @Override
    public double compute(double arg1, double arg2) {
        return arg1 * arg2;
    }
}
