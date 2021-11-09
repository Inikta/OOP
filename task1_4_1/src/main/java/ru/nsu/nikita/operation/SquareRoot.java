package ru.nsu.nikita.operation;

public class SquareRoot extends Operation {
    public SquareRoot() {
        this.unary = true;
    }

    @Override
    public double compute(double arg1) {
        return Math.sqrt(arg1);
    }
}
