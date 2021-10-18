package ru.nsu.nikita;

public class IncorrectTableException extends Exception {        //exception for incorrect data input

    public IncorrectTableException(String message) {
        super(message);
    }
}
