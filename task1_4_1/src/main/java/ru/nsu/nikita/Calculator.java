package ru.nsu.nikita;

import ru.nsu.nikita.operation.*;

public class Calculator {
    private final String expression;
    private double result;
    private int offset;

    /*
     * Initialize and compute expression result
     */
    public Calculator(String expression) {
        this.expression = expression + "\0";
        this.result = 0;
        this.offset = 0;
    }

    public void compute() {
        this.result = Pair();
    }

    public double getResult() {
        return result;
    }

    /*
     * Reads number (double or int) and moves offset in string
     */
    private String readNum() {
        StringBuilder res = new StringBuilder();
        while (expression.charAt(offset) >= '0' && expression.charAt(offset) <= '9' || expression.charAt(offset) == '.') {
            res.append(expression.charAt(offset++));
        }
        return res.toString();
    }

    /*
     * Determines how next argument should be interpreted by comparing string part beginning
     * from current offset with references:
     * 1. Returns "num", if digit was found
     * 2. Returns "end", if "\0" or "\n" were found
     * 3. Returns the operation name, if predefined operation name was found
     * Else throws IllegalArgumentException
     */
    private String check() {
        while (expression.charAt(offset) == ' ') {
            offset++;
        }

        if (expression.charAt(offset) >= '0' && expression.charAt(offset) <= '9') {
            return "num";
        } else if (expression.charAt(offset) == '\0' || expression.charAt(offset) == '\n') {
            return "end";
        } else {
            String[] bundle = OperationFabric.allOperations;
            for (String op : bundle) {
                if (expression.regionMatches(offset, op, 0, op.length())) {
                    offset += op.length();
                    return op;
                }
            }
            throw new IllegalArgumentException();
        }

    }

    /*
     * Inner class, which recursively allows computing of expression result.
     * Checks what to do, and returns double value on every recursion step.
     * 1. Returns double value, if it is number.
     * 2. Returns 0, if end of expression was found.
     * 3. Gets operation by OperationFabric, checks if it is unary or not and makes computation.
     */
    private double Pair() {
        double arg1;
        double arg2;

        String operation = check();

        if (operation.equals("num")) {
            return Double.parseDouble(readNum());
        } else if (operation.equals("end")) {
            return 0;
        } else {
            Operation currentOperation = OperationFabric.getOperation(operation);
            arg1 = Pair();
            if (!currentOperation.unary) {
                arg2 = Pair();
                return currentOperation.compute(arg1, arg2);
            } else {
                return currentOperation.compute(arg1);
            }
        }
    }
}