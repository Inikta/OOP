package ru.nsu.nikita;

import java.io.IOException;

public class App {
    /**
     * Just an app for normal executing.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) {
        PizzeriasMaker pizzeriasMaker = new PizzeriasMaker("data");
        Pizzeria pizzeria = pizzeriasMaker.makePizzeria(1);
        pizzeria.start();
    }
}
