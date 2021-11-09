package ru.nsu.nikita.operation;

public class LogN extends Operation {
    public LogN() {
        this.unary = true;
    }

    @Override
    public double compute(double arg1) {
        return Math.log(arg1);
    }
}
