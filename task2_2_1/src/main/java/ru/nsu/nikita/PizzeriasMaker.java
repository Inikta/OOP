package ru.nsu.nikita;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.nsu.nikita.employee.BakerAttributes;
import ru.nsu.nikita.employee.SupplierAttributes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PizzeriasMaker {
    private final GsonBuilder builder;
    private final String folder;
    private Path path;

    /**
     * Standard constructor.
     * @param folder name of the folder, in which files will be created/searched.
     */
    public PizzeriasMaker(String folder) {
        this.folder = folder;
        this.builder = new GsonBuilder();
    }

    /**
     * Create pizzeria, save it to json file as PizzeriaAttributes and return it fo further usage.
     * @param fileName name of the created file.
     * @return Pizzeria object.
     * @throws IOException if something goes wrong during setting the pizzeria attributes through usr input.
     */

    public Pizzeria makeNewPizzeriaToFile(String fileName) throws IOException {
        PizzeriaAttributes attributes = makePizzeriaConfiguration();
        Pizzeria pizzeria = new Pizzeria(attributes);

        createFile(fileName, attributes);

        return pizzeria;
    }

    /**
     * Create pizzeria, save it to json file as PizzeriaAttributes without returning.
     * @param fileName name of the created file.
     * @throws IOException if something goes wrong during setting the pizzeria attributes through usr input.
     */

    public void savePizzeriaToFile(Pizzeria pizzeria, String fileName) throws IOException {
        Gson gson = builder.create();
        PizzeriaAttributes attributes = pizzeria.getAttributes();
        createFile(fileName, attributes);
    }

    /**
     * Create new pizzeria from file with its attributes.
     * @param fileName file with attributes.
     * @return pizzeria object with used attributes.
     * @throws FileNotFoundException
     */

    public Pizzeria makePizzeriaFromFile(String fileName) throws FileNotFoundException {
        Gson gson = builder.create();
        String delimiter = FileSystems.getDefault().getSeparator();
        return new Pizzeria(gson.fromJson(new FileReader(".." + delimiter + "task2_2_1" + delimiter + folder + delimiter + fileName + ".json"), PizzeriaAttributes.class));
    }

    /**
     * Create json file, which contains pizzeria attributes.
     * @param fileName name of the file with attributes.
     * @param attributes data structure with all necessary pizzeria attributes.
     * @throws IOException
     */
    private void createFile(String fileName, PizzeriaAttributes attributes) throws IOException {
        String delimiter = FileSystems.getDefault().getSeparator();
        Path folderPath = FileSystems.getDefault().getPath(".." + delimiter + folder);
        path = FileSystems.getDefault().getPath(".."  + delimiter + "task2_2_1" + delimiter + folder + delimiter + fileName + ".json");

        Gson gson = builder.create();

        //create directories path if it does not exist
        if (!Files.exists(folderPath)) {
            Files.createDirectories(folderPath);
        }

        //create file and write new pizzeria's json representation to it
        Files.deleteIfExists(path);
        Files.createFile(path);

        FileWriter fileWriter = new FileWriter(path.toString());
        fileWriter.write(gson.toJson(attributes, PizzeriaAttributes.class));
    }

    /**
     * Create new pizzeria. Attributes are obtained from user input in console.
     * @return new Pizzeria object with inferred parameters.
     */

    public Pizzeria makeNewPizzeria() {
        return new Pizzeria(makePizzeriaConfiguration());
    }

    /**
     * Create pizzeria configuration data structure, which contains all necessary attributes such as:
     *  - amount of bakers;
     *  - amount of suppliers;
     *  - storage size limit;
     *  - orders amount in orders queue.
     *  In addition, the type of bakers and suppliers parameters generation will be offered: manual(not working) or automatic.
     * @return data structure with attributes.
     */

    public PizzeriaAttributes makePizzeriaConfiguration() {
        PizzeriaAttributes attributes = new PizzeriaAttributes();

        Scanner reader = new Scanner(System.in);
        System.out.println("Configure Pizzeria:");

        System.out.print("\tAmount of bakers: ");
        int bakersAmount = reader.nextInt();

        System.out.print("\tAmount of suppliers: ");
        int suppliersAmount = reader.nextInt();

        System.out.print("\tStorage limit: ");
        int storageLimit = reader.nextInt();

        System.out.print("\tOrders amount: ");
        int ordersAmount = reader.nextInt();

        attributes.setBakersAmount(bakersAmount);
        attributes.setSuppliersAmount(suppliersAmount);
        attributes.setStorageLimit(storageLimit);
        attributes.setOrdersAmount(ordersAmount);

        System.out.println(
                "[0] - Automatic configuration of bakers and suppliers\n" +
                "[1] - Manual configuration of bakers and suppliers");

        int mode = reader.nextInt();
        configureBakers(attributes, mode, reader);
        configureSuppliers(attributes, mode, reader);

        reader.close();

        return attributes;
    }

    /**
     * Configure all bakers from user input or by random generation.
     * @param attributes data structure, in which parameters will be contained.
     * @param mode mode of generation.
     * @param reader scanner object for reading user input.
     */
    private void configureBakers(PizzeriaAttributes attributes, int mode, Scanner reader) {
        List<BakerAttributes> bakersAttributes = new ArrayList<>();
        System.out.println("Configure bakers.");
        if (mode == 1) {
            for (int i = 0; i < attributes.getBakersAmount(); i++) {
                System.out.print("\tBaker #" + i + " - bake time: ");
                int newBakeTime = reader.nextInt();
                //System.out.println();

                bakersAttributes.add(new BakerAttributes(i, newBakeTime));
            }
        } else {
            for (int i = 0; i < attributes.getBakersAmount(); i++) {
                System.out.print("\tBaker #" + i + " - bake time: ");
                int newBakeTime = Math.abs(((Double) (Math.random() * 10000)).intValue());
                System.out.println(newBakeTime);

                bakersAttributes.add(new BakerAttributes(i, newBakeTime));
            }
        }

        attributes.setBakerAttributes(bakersAttributes);
    }

    /**
     * Configure all suppliers from user input or by random generation.
     * @param attributes data structure, in which parameters will be contained.
     * @param mode mode of generation.
     * @param reader scanner object for reading user input.
     */

    private void configureSuppliers(PizzeriaAttributes attributes, int mode, Scanner reader) {
        List<SupplierAttributes> suppliersAttributes = new ArrayList<>();
        System.out.println("Configure suppliers.");
        if (mode == 1) {
            for (int i = 0; i < attributes.getSuppliersAmount(); i++) {
                System.out.println("\tSupplier #" + i);
                System.out.print("\t\tBag size: ");
                int newBagSize = reader.nextInt();

                System.out.print("\t\tDelivery time: ");
                int newDeliverTime = reader.nextInt();

                System.out.print("\t\tWaiting time: ");
                int newWaitingTime = reader.nextInt();

                suppliersAttributes.add(new SupplierAttributes(i, newBagSize, newDeliverTime, newWaitingTime));
            }
        } else {
            for (int i = 0; i < attributes.getSuppliersAmount(); i++) {
                System.out.println("\tSupplier #" + i);
                int newBagSize = Math.abs(((Double) (Math.random() * 10)).intValue());
                System.out.println("\t\tBag size: " + newBagSize);

                int newDeliverTime = Math.abs(((Double) (Math.random() * 10000)).intValue());
                System.out.println("\t\tDelivery time: " + newDeliverTime);

                int newWaitingTime = Math.abs(((Double) (Math.random() * 1000)).intValue());
                System.out.println("\t\tWaiting time: " + newWaitingTime);

                suppliersAttributes.add(new SupplierAttributes(i, newBagSize, newDeliverTime, newWaitingTime));
            }
        }

        attributes.setSupplierAttributes(suppliersAttributes);
    }
}
