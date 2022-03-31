package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PizzeriaTests {

    @Test
    public void creationTest() {
        PizzeriasMaker pizzeriasMaker = new PizzeriasMaker("data");
        Pizzeria pizzeria = pizzeriasMaker.makePizzeria(0);
    }

    @Test
    public void notExistingFolderMakerTest() {
        Assertions.assertThrows(FileNotFoundException.class, () -> new PizzeriasMaker("Schrodinger"));
    }

    @Test
    public void fromFileTest() throws IOException {
        PizzeriasMaker pizzeriasMaker = new PizzeriasMaker("data");
        Pizzeria pizzeria1 = pizzeriasMaker.makePizzeria(0);
        pizzeriasMaker.savePizzeriaToFile(pizzeria1, "fromFileTest");

        Pizzeria pizzeria2 = pizzeriasMaker.makePizzeriaFromFile("fromFileTest");

        Assertions.assertEquals(pizzeria1, pizzeria2);
    }

    @Test
    public void toFileTest() throws IOException {
        PizzeriasMaker pizzeriasMaker = new PizzeriasMaker("data");
        Pizzeria pizzeria = pizzeriasMaker.makePizzeriaToFile("toFileTest", 0);

        Assertions.assertTrue(Files.exists(Paths.get("data", "toFileTest.json")));
    }

    @Test
    public void saveToFileTest() throws IOException {
        PizzeriasMaker pizzeriasMaker = new PizzeriasMaker("data");
        Pizzeria pizzeria1 = pizzeriasMaker.makePizzeria(0);
        pizzeriasMaker.savePizzeriaToFile(pizzeria1, "saveToFileTest");

        Pizzeria pizzeria2 = pizzeriasMaker.makePizzeriaFromFile("saveToFileTest");

        Assertions.assertEquals(pizzeria1, pizzeria2);
    }

    @Test
    public void saveToNullFileTest() {
        PizzeriasMaker pizzeriasMaker = new PizzeriasMaker("data");
        Pizzeria pizzeria = pizzeriasMaker.makePizzeria(0);
        Assertions.assertThrows(NullPointerException.class, () -> pizzeriasMaker.savePizzeriaToFile(pizzeria, null));
    }
}
