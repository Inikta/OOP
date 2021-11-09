package ru.nsu.nikita.operation;

public class Division extends Operation {
    public Division() {
        this.unary = false;
    }

    @Override
    public double compute(double arg1, double arg2) {
        return arg1 / arg2;
    }
}
