package ru.nsu.nikita.operation;

public class ToDegrees extends Operation {
    public ToDegrees() {
        this.unary = true;
    }

    @Override
    public double compute(double arg1) {
        return arg1 / 180 * Math.PI;
    }
}
