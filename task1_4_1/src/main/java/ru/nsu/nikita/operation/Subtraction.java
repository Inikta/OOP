package ru.nsu.nikita.operation;

public class Subtraction extends Operation  {

    @Override
    public double compute(double arg1) {
        return -arg1;
    }

    @Override
    public double compute(double arg1, double arg2) {
        return arg1 - arg2;
    }
}
