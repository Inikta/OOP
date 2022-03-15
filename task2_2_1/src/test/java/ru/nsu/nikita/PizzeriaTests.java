package ru.nsu.nikita;

import org.junit.jupiter.api.Test;

public class PizzeriaTests {

    @Test
    void staticOrdersGenerationWorkTest() {
        Pizzeria pizzeria = new Pizzeria(3, 2, 3, 30, 30, 30, 1);
        pizzeria.startWork(0);
    }
}
