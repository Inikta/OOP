package ru.nsu.nikita.operation;

/*
* Parent class for all operations.
* Unary variable indicates if operation should take one or two arguments.
 */

public class Operation {
    public boolean unary;

    public Operation() {
    }

    public double compute(double arg1) {
        return 0;
    }


    public double compute(double arg1, double arg2) {
        return 0;
    }
}
