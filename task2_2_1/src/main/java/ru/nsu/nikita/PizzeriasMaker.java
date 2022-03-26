package ru.nsu.nikita;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PizzeriasMaker {
    private static final GsonBuilder builder = new GsonBuilder();
    //private Pizzeria pizzeria;

    public static Pizzeria makePizzeriaFromFile(String fileName) throws FileNotFoundException {
        Gson gson = builder.create();
        return gson.fromJson(new FileReader("../data/" + fileName + ".json"), Pizzeria.class);
    }

    public static Pizzeria makeNewPizzeria(int bakersAmount,
                                 int suppliersAmount,
                                 int storageLimit) {
        return new Pizzeria(bakersAmount, suppliersAmount, storageLimit);
    }

    public static Pizzeria makeNewPizzeriaToFile(int bakersAmount,
                                 int suppliersAmount,
                                 int storageLimit,
                                 String fileName) throws IOException {
        Pizzeria pizzeria = new Pizzeria(bakersAmount, suppliersAmount, storageLimit);
        BufferedWriter fileWriter = Files.newBufferedWriter("../data/" + fileName + ".json")

        Gson gson = builder.create();
        fileWriter.write(gson.toJson(pizzeria));

        return pizzeria;
    }

    public static void savePizzeriaToFile(Pizzeria pizzeria, String fileName) throws IOException {
        Gson gson = builder.create();
        FileWriter fileWriter = new FileWriter(fileName + ".json");
        fileWriter.write(gson.toJson(pizzeria));
    }
}
