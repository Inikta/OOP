package ru.nsu.nikita.operation;

public class Sinus extends Operation {
    @Override
    public double compute(double arg1) {
        return Math.sin(arg1);
    }

    @Override
    public double compute(double arg1, double arg2) {
        return this.compute(arg1);
    }
}
