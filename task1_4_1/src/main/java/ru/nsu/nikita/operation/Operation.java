package ru.nsu.nikita.operation;

/*
* Parent class for all operations
 */
public class Operation implements UnaryOperation, BinaryOperation {
    @Override
    public double compute(double arg1) {
        return 0;
    }

    @Override
    public double compute(double arg1, double arg2) {
        return 0;
    }
}
