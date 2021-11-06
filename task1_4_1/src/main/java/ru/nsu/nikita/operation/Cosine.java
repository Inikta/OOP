package ru.nsu.nikita.operation;

public class Cosine extends Operation {
    @Override
    public double compute(double arg1) {
        return Math.cos(arg1);
    }

    @Override
    public double compute(double arg1, double arg2) {
        return this.compute(arg1);
    }
}
