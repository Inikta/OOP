package ru.nsu.nikita;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.JsonAdapter;
import ru.nsu.nikita.employee.Baker;
import ru.nsu.nikita.employee.BakerAttributes;
import ru.nsu.nikita.employee.Supplier;
import ru.nsu.nikita.employee.SupplierAttributes;
import ru.nsu.nikita.order_generators.AutoGenerator;
import ru.nsu.nikita.order_generators.ManualGenerator;
import ru.nsu.nikita.order_generators.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Pizzeria {

    private final int bakersAmount;
    private final int suppliersAmount;
    private final Deque<Order> ordersQueue;
    private final Deque<Order> storageQueue;
    public AtomicInteger orderCounter;
    //GsonBuilder builder;
    private List<BakerAttributes> bakersAttributes;
    private List<SupplierAttributes> suppliersAttributes;
    private int storageLimit;

    public Pizzeria(int bakersAmount,
                    int suppliersAmount,
                    int storageLimit) {
        this.bakersAmount = bakersAmount;
        this.suppliersAmount = suppliersAmount;

        this.storageLimit = storageLimit;

        ordersQueue = new ArrayDeque<>();
        storageQueue = new ArrayDeque<>(storageLimit);

        orderCounter = new AtomicInteger(0);

        Scanner reader = new Scanner(System.in);

        bakersAttributes = new ArrayList<>();
        suppliersAttributes = new ArrayList<>();

        System.out.println("[0] - Automatic configuration of bakers and suppliers\n" +
                "[1] - Manual configuration of bakers and suppliers");
        int param = reader.nextInt();
        reader.close();

        configureBakers(param);
        configureSuppliers(param);
    }

    /*public void saveToJson(String fileName) throws IOException {
        builder = new GsonBuilder();
        String data = builder.create().toJson(this);
        File file = new File("../data/" + fileName + ".json");
        file.createNewFile();
        file.setReadable(true);
        file.setWritable(true);

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(data);
    }*/

    private void configureBakers(int mode) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Configure bakers.");
        if (mode == 1) {
            for (int i = 0; i < bakersAmount; i++) {
                System.out.print("Baker #" + i + " - bake time: ");
                int newBakeTime = reader.nextInt();

                bakersAttributes.add(new BakerAttributes(i, newBakeTime));
            }
        } else {
            for (int i = 0; i < bakersAmount; i++) {
                System.out.print("Baker #" + i + " - bake time: ");
                int newBakeTime = Math.abs(((Double) (Math.random() * 10000)).intValue());
                System.out.print(newBakeTime);

                bakersAttributes.add(new BakerAttributes(i, newBakeTime));
            }
        }
        reader.close();
    }

    private void configureSuppliers(int mode) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Configure suppliers.");
        if (mode == 1) {
            for (int i = 0; i < suppliersAmount; i++) {
                System.out.println("Supplier #" + i);
                System.out.print("\tBag size: ");
                int newBagSize = reader.nextInt();

                System.out.print("\tDelivery time: ");
                int newDeliverTime = reader.nextInt();

                suppliersAttributes.add(new SupplierAttributes(i, newBagSize, newDeliverTime));
            }
        } else {
            for (int i = 0; i < suppliersAmount; i++) {
                System.out.println("Supplier #" + i);
                int newBagSize = Math.abs(((Double) (Math.random() * 10)).intValue());
                System.out.println("\tBag size: " + newBagSize);

                int newDeliverTime = Math.abs(((Double) (Math.random() * 10000)).intValue());
                System.out.println("\tDelivery time: " + newDeliverTime);

                suppliersAttributes.add(new SupplierAttributes(i, newBagSize, newDeliverTime));
            }
        }
        reader.close();
    }

    private List<Baker> createBakers() {
        System.out.println("Creating bakers...");
        List<Baker> bakersList = new ArrayList<>();
        for (BakerAttributes attr : bakersAttributes) {
            bakersList.add(new Baker(attr, this));
        }
        System.out.println("Done!");

        return bakersList;
    }

    private List<Supplier> createSuppliers() {
        System.out.println("Creating suppliers...");
        List<Supplier> supplierList = new ArrayList<>();
        for (SupplierAttributes attr : suppliersAttributes) {
            supplierList.add(new Supplier(attr, this));
        }
        System.out.println("Done!");
        return supplierList;
    }

    public void startWork() {
        Scanner reader = new Scanner(System.in);
        AutoGenerator autoGenerator;
        ManualGenerator manualGenerator;

        List<Baker> bakerList = createBakers();
        List<Supplier> supplierList = createSuppliers();

        System.out.print("""
                Generate orders automatically [0]?
                OR
                Generate orders manually [1] [Disabled]?
                """);
        int mode = reader.nextInt();
        if (mode == 0) {
            autoGenerator = new AutoGenerator(this);

            System.out.println("How much orders to generate?");
            int ordersAmount = reader.nextInt();
            autoGenerator.generate(ordersAmount);
        } else if (mode == 1) {
            manualGenerator = new ManualGenerator(this, reader);
            manualGenerator.start();
        }

        supplierList.forEach(t -> t.setPriority(9));

        bakerList.forEach(Thread::start);
        supplierList.forEach(Thread::start);
        reader.close();
    }

    public int getBakersAmount() {
        return bakersAmount;
    }

    public int getSuppliersAmount() {
        return suppliersAmount;
    }

    public int getStorageLimit() {
        return storageLimit;
    }

    public void setStorageLimit(int storageLimit) {
        this.storageLimit = storageLimit;
    }

    public List<BakerAttributes> getBakerAttributes() {
        return bakersAttributes;
    }

    public List<SupplierAttributes> getSuppliersAttributes() {
        return suppliersAttributes;
    }

    public Deque<Order> getOrdersQueue() {
        return ordersQueue;
    }

    public Deque<Order> getStorageQueue() {
        return storageQueue;
    }
}
