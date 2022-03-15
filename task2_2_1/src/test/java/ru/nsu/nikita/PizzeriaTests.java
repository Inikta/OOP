package ru.nsu.nikita;

import org.junit.jupiter.api.Test;

public class PizzeriaTests {

    @Test
    void staticOrdersGenerationWorkTest() {
        Pizzeria pizzeria = new Pizzeria(1, 1, 3, 30, 10, 30, 1);
        pizzeria.startWork(0);
    }
}
