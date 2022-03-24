package ru.nsu.nikita;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PizzeriasMaker {
    private final GsonBuilder builder = new GsonBuilder();
    //private Pizzeria pizzeria;

    public Pizzeria makePizzeria(String fileName) throws FileNotFoundException {
        Gson gson = builder.create();
        return gson.fromJson(new FileReader("../data/" + fileName + ".json"), Pizzeria.class);
    }

    public Pizzeria makePizzeria(int bakersAmount,
                                 int suppliersAmount,
                                 int storageLimit) {
        return new Pizzeria(bakersAmount, suppliersAmount, storageLimit);
    }

    public Pizzeria makePizzeria(int bakersAmount,
                                 int suppliersAmount,
                                 int storageLimit,
                                 String fileName) throws IOException {
        Pizzeria pizzeria = new Pizzeria(bakersAmount, suppliersAmount, storageLimit);
        FileWriter fileWriter = new FileWriter(fileName + ".json");

        Gson gson = builder.create();
        fileWriter.write(gson.toJson(pizzeria));

        return pizzeria;
    }
}
