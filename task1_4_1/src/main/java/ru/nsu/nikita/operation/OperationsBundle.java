package ru.nsu.nikita.operation;

public class OperationsBundle {
    public static String[] operations =
            {"+", "-", "*", "/", "pow", "log",
                    "sin", "cos", "deg", "sqrt"};
    public static String[] unaryOperations = {"log", "sin", "cos", "deg", "sqrt"};
    public static String[] binaryOperations = {"+", "-", "*", "/", "pow"};

    public static String[] getAllOperations() {
        return operations;
    }

    public static String[] getUnaryOperations() {
        return unaryOperations;
    }

    public static String[] getBinaryOperations() {
        return binaryOperations;
    }
}
