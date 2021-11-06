package ru.nsu.nikita.operation;

public class LogN extends Operation {
    @Override
    public double compute(double arg1) {
        return Math.log(arg1);
    }

    @Override
    public double compute(double arg1, double arg2) {
        return this.compute(arg1);
    }
}
