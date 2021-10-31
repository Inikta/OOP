package ru.nsu.nikita;

import ru.nsu.nikita.operation.Operations;

public class Calculator {
    private final String expression;
    private double result;
    private int offset;

    public Calculator(String expression) {
        this.expression = expression + "\0";
        this.result = 0;
        this.offset = 0;
        Pair expressionParser = new Pair();
        this.result = expressionParser.result;
    }

    public double getResult() {
        return result;
    }

    private String readNum() {
        StringBuilder res = new StringBuilder();
        while (expression.charAt(offset) >= '0' && expression.charAt(offset) <= '9' || expression.charAt(offset) == '.') {
            res.append(expression.charAt(offset++));
        }
        return res.toString();
    }

    private int check() {
        while (expression.charAt(offset) == ' ') {
            offset++;
        }

        if (expression.charAt(offset) >= '0' && expression.charAt(offset) <= '9') {
            return 0;
        } else {
            int opCode = 1;
            for (String op : Operations.unary) {
                if (expression.regionMatches(offset, op, 0, op.length())) {
                    offset += op.length();
                    return opCode + 100;
                }
                opCode++;
            }

            opCode = 1;
            for (String op : Operations.binary) {
                if (expression.regionMatches(offset, op, 0, op.length())) {
                    offset += op.length();
                    return opCode + 200;
                }
                opCode++;
            }
        }

        throw new IllegalArgumentException();
    }

    private class Pair {
        public double result;

        private Pair() {
            double elem1;
            double elem2;

            int sign = check();
            switch (sign) {
                case 0 -> {
                    elem1 = Double.parseDouble(readNum());
                    this.result = elem1;
                }
                case 101 -> {
                    elem1 = Operations.sinus(new Pair().result);
                    this.result = elem1;
                }
                case 102 -> {
                    elem1 = Operations.cosine(new Pair().result);
                    this.result = elem1;
                }
                case 103 -> {
                    elem1 = Operations.sqrt(new Pair().result);
                    this.result = elem1;
                }
                case 104 -> {
                    elem1 = new Pair().result;
                    this.result = Operations.naturalLog(elem1);
                }
                case 105 -> {
                    elem1 = Operations.convertDegToDouble(Double.parseDouble(readNum()));
                    this.result = elem1;
                }
                case 201 -> {
                    elem1 = new Pair().result;
                    elem2 = new Pair().result;
                    this.result = Operations.power(elem1, elem2);
                }
                case 202 -> {
                    elem1 = new Pair().result;
                    elem2 = new Pair().result;
                    this.result = Operations.addition(elem1, elem2);
                }
                case 203 -> {
                    elem1 = new Pair().result;
                    elem2 = new Pair().result;
                    this.result = Operations.subtraction(elem1, elem2);
                }
                case 204 -> {
                    elem1 = new Pair().result;
                    elem2 = new Pair().result;
                    this.result = Operations.production(elem1, elem2);
                }
                case 205 -> {
                    elem1 = new Pair().result;
                    elem2 = new Pair().result;
                    this.result = Operations.division(elem1, elem2);
                }
                default -> throw new IllegalArgumentException();
            }
        }
    }

}