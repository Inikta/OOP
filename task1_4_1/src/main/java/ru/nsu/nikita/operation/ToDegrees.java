package ru.nsu.nikita.operation;

public class ToDegrees extends Operation {
    @Override
    public double compute(double arg1) {
        return arg1 / 180 * Math.PI;
    }

    @Override
    public double compute(double arg1, double arg2) {
        return this.compute(arg1);
    }
}
