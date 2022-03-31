package ru.nsu.nikita;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PizzeriaTests {

    public static void main(String[] args) throws IOException {
        PizzeriasMaker pizzeriasMaker = new PizzeriasMaker("data");
        Pizzeria pizzeria = pizzeriasMaker.makeNewPizzeriaToFile("pizza1");
        pizzeria.start();
    }
}
