package ru.nsu.nikita;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PizzeriasMaker {
    private static String folder = "data";
    private static Path path;
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
        //create paths for folder with data and file itself
        String delimiter = FileSystems.getDefault().getSeparator();
        Path folderPath = FileSystems.getDefault().getPath(".." + delimiter + folder);
        path =  FileSystems.getDefault().getPath(".." + delimiter + folder + delimiter + fileName + ".json");

        //create new pizzeria and json builder for it
        Pizzeria pizzeria = new Pizzeria(bakersAmount,suppliersAmount,storageLimit);
        Gson gson = builder.create();

        //create directories path if it does not exist
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        //create file and write new pizzeria's json representation to it
        Files.deleteIfExists(path);
        Files.createFile(path);

        FileWriter fileWriter = new FileWriter(path.toString());
        fileWriter.write(gson.toJson(pizzeria, Pizzeria.class));

        return pizzeria;
    }

    public static Pizzeria makeNewPizzeriaToFile(int bakersAmount,
                                                 int suppliersAmount,
                                                 int storageLimit,
                                                 String pathStr,
                                                 String fileName) throws IOException {
        //create paths for folder with data and file itself
        String delimiter = FileSystems.getDefault().getSeparator();
        Path folderPath = FileSystems.getDefault().getPath(pathStr);
        path =  FileSystems.getDefault().getPath(pathStr + delimiter + fileName + ".json");

        //create new pizzeria and json builder for it
        Pizzeria pizzeria = new Pizzeria(bakersAmount,suppliersAmount,storageLimit);
        Gson gson = builder.create();

        //create directories path if it does not exist
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        //create file and write new pizzeria's json representation to it
        Files.createFile(path);

        FileWriter fileWriter = new FileWriter(path.toString());
        fileWriter.write(gson.toJson(pizzeria));

        return pizzeria;
    }

    public static void savePizzeriaToFile(Pizzeria pizzeria, String fileName) throws IOException {
        Gson gson = builder.create();

    }
}
