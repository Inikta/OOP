package ru.nsu.nikita.operation;

public class Sinus extends Operation {
    public Sinus() {
        this.unary = true;
    }

    @Override
    public double compute(double arg1) {
        return Math.sin(arg1);
    }
}
