package ru.nsu.nikita.operation;

public class Cosine extends Operation {
    public Cosine() {
        this.unary = true;
    }
    @Override
    public double compute(double arg1) {
        return Math.cos(arg1);
    }

}
