package ru.nsu.nikita.operation;

/*
* Fabric for all operations with global array of them.
 */

public class OperationFabric {

    public static final String[] allOperations = {
            "sin", "cos", "sqrt", "deg", "log",
            "+", "-", "*", "/", "pow"};

    public static Operation getOperation(String operation) {
            switch (operation) {
                case "sin" -> {return new Sinus();}
                case "cos" -> {return new Cosine();}
                case "deg" -> {return new ToDegrees();}
                case "sqrt" -> {return new SquareRoot();}
                case "+" -> {return new Addition();}
                case "-" -> {return new Subtraction();}
                case "*" -> {return new Production();}
                case "/" -> {return new Division();}
                case "pow" -> {return new Exponentiation();}
                case "log" -> {return new LogN();}
                default -> throw new IllegalArgumentException();
            }
    }
}
