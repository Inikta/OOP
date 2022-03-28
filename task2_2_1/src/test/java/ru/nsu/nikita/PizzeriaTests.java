package ru.nsu.nikita;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class PizzeriaTests {

    public static void main(String[] args) throws IOException {
        int bakersAmount = 3;
        int suppliersAmount = 3;
        int storageSize = 5;
        Pizzeria pizzeria = PizzeriasMaker.makeNewPizzeria(bakersAmount, suppliersAmount, storageSize);
        //pizzeria.saveToJson("pizza1");
        pizzeria.startWork();
    }
}
