package ru.nsu.nikita;

import ru.nsu.nikita.employee.Baker;
import ru.nsu.nikita.employee.Supplier;
import ru.nsu.nikita.order_generators.AutoGenerator;
import ru.nsu.nikita.order_generators.ManualGenerator;
import ru.nsu.nikita.order_generators.Order;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Pizzeria extends Thread {

    private final int bakersAmount;
    private final int suppliersAmount;
    private final Deque<Order> ordersQueue;
    private final Deque<Order> storageQueue;
    public volatile AtomicInteger orderCounter;
    private List<Baker> bakers;
    private List<Supplier> suppliers;
    private int storageLimit;

    public Pizzeria(int bakersAmount,
                    int suppliersAmount,
                    int storageLimit) {
        this.bakersAmount = bakersAmount;
        this.suppliersAmount = suppliersAmount;

        this.storageLimit = storageLimit;

        ordersQueue = new ArrayDeque<>();
        storageQueue = new ArrayDeque<>(storageLimit);

        orderCounter.set(0);

        configureBakers();
        configureSuppliers();
    }

    private void configureBakers() {
        System.out.println("Configure bakers.");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < bakersAmount; i++) {
            System.out.println("Bake time: ");
            int newBakeTime = scanner.nextInt();

            bakers.add(new Baker(i, newBakeTime, this));
        }
    }

    private void configureSuppliers() {
        System.out.println("Configure suppliers.");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < suppliersAmount; i++) {
            System.out.println("Bag size: ");
            int newBagSize = scanner.nextInt();

            System.out.println("Delivery time: ");
            int newDeliverTime = scanner.nextInt();

            suppliers.add(new Supplier(i, newBagSize, newDeliverTime, this));
        }
    }

    @Override
    public void run() {
        AutoGenerator autoGenerator;
        ManualGenerator manualGenerator;
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                Generate orders automatically [0]?
                OR
                Generate orders manually [1]?
                """);
        int mode = scanner.nextInt();
        if (mode == 0) {
            autoGenerator = new AutoGenerator(this);

            System.out.println("How much orders to generate?");
            int ordersAmount = scanner.nextInt();
            autoGenerator.generate(ordersAmount);
        } else if (mode == 1) {
            manualGenerator = new ManualGenerator(this);
            manualGenerator.start();
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
