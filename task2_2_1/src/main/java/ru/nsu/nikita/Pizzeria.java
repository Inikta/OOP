package ru.nsu.nikita;

import ru.nsu.nikita.employee.Baker;
import ru.nsu.nikita.employee.Supplier;
import ru.nsu.nikita.order_generators.AutoGenerator;
import ru.nsu.nikita.order_generators.ManualGenerator;
import ru.nsu.nikita.order_generators.Order;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Pizzeria {

    private final int bakersAmount;
    private final int suppliersAmount;
    private final Deque<Order> ordersQueue;
    private final Deque<Order> storageQueue;
    public AtomicInteger orderCounter;
    private List<Baker> bakers;
    private List<Supplier> suppliers;
    private int storageLimit;

    private Scanner reader;

    public Pizzeria(int bakersAmount,
                    int suppliersAmount,
                    int storageLimit) {
        this.bakersAmount = bakersAmount;
        this.suppliersAmount = suppliersAmount;

        this.storageLimit = storageLimit;

        ordersQueue = new ArrayDeque<>();
        storageQueue = new ArrayDeque<>(storageLimit);

        orderCounter = new AtomicInteger(0);

        reader = new Scanner(System.in);

        bakers = new ArrayList<>();
        suppliers = new ArrayList<>();

        configureBakers();
        configureSuppliers();
    }

    private void configureBakers() {
        System.out.println("Configure bakers.");
        for (int i = 0; i < bakersAmount; i++) {
            System.out.println("Baker #" + i + " - bake time: ");
            int newBakeTime = reader.nextInt();

            bakers.add(new Baker(i, newBakeTime, this));
        }
    }

    private void configureSuppliers() {
        System.out.println("Configure suppliers.");
        for (int i = 0; i < suppliersAmount; i++) {
            System.out.println("Supplier #" + i);
            System.out.println("\tBag size: ");
            int newBagSize = reader.nextInt();

            System.out.println("\tDelivery time: ");
            int newDeliverTime = reader.nextInt();

            suppliers.add(new Supplier(i, newBagSize, newDeliverTime, this));
        }
    }

    public void startWork() {
        AutoGenerator autoGenerator;
        ManualGenerator manualGenerator;

        System.out.println("""
                Generate orders automatically [0]?
                OR
                Generate orders manually [1]?
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

        for (Baker baker : bakers) {
            baker.start();
        }

        for (Supplier supplier : suppliers) {
            supplier.start();
        }
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

    public List<Baker> getBakers() {
        return bakers;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public Deque<Order> getOrdersQueue() {
        return ordersQueue;
    }

    public Deque<Order> getStorageQueue() {
        return storageQueue;
    }
}
