package ru.nsu.nikita.operation;

public class Addition extends Operation {
    @Override
    public double compute(double arg1, double arg2) {
        return arg1 + arg2;
    }
}
