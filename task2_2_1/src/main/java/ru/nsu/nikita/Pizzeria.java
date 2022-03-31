package ru.nsu.nikita;

import ru.nsu.nikita.employee.Baker;
import ru.nsu.nikita.employee.BakerAttributes;
import ru.nsu.nikita.employee.Supplier;
import ru.nsu.nikita.employee.SupplierAttributes;
import ru.nsu.nikita.order_generators.AutoGenerator;
import ru.nsu.nikita.order_generators.ManualGenerator;
import ru.nsu.nikita.order_generators.Order;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Pizzeria extends Thread {

    private final PizzeriaAttributes attributes;

    private final Deque<Order> ordersQueue;
    private final Deque<Order> storageQueue;
    public AtomicInteger orderCounter;

    /**
     * Standard constructor of pizzeria, which initializes queue of orders, storage queue and order counter.
     * @param attributes data structure with all parameters of pizzeria.
     */

    public Pizzeria(PizzeriaAttributes attributes) {
        this.attributes = attributes;

        ordersQueue = new ArrayDeque<>();
        storageQueue = new ArrayDeque<>(attributes.getStorageLimit());

        orderCounter = new AtomicInteger(0);
    }

    /**
     * Create baker threads.
     * @return list of threads.
     */

    private List<Baker> createBakers() {
        System.out.println("Creating bakers...");
        List<Baker> bakersList = new ArrayList<>();
        for (BakerAttributes attr : attributes.getBakerAttributes()) {
            bakersList.add(new Baker(attr, this));
        }
        System.out.println("Done!");

        return bakersList;
    }

    /**
     * Create suppliers threads.
     * @return list of threads.
     */

    private List<Supplier> createSuppliers() {
        System.out.println("Creating suppliers...");
        List<Supplier> supplierList = new ArrayList<>();
        for (SupplierAttributes attr : attributes.getSupplierAttributes()) {
            supplierList.add(new Supplier(attr, this));
        }
        System.out.println("Done!");
        return supplierList;
    }

    @Override
    public void run() {
        //Scanner reader = new Scanner(System.in);
        AutoGenerator autoGenerator;
        ManualGenerator manualGenerator;

        List<Baker> bakerList = createBakers();
        List<Supplier> supplierList = createSuppliers();

        System.out.print("""
                Generate orders automatically [0]?
                OR
                Generate orders manually [1] [Disabled]?
                """);
        //int mode = reader.nextInt();
        //if (mode == 0) {
        autoGenerator = new AutoGenerator(this);
        autoGenerator.generate(attributes.getOrdersAmount());
        /*} else if (mode == 1) {
            manualGenerator = new ManualGenerator(this, reader);
            manualGenerator.start();
        }*/

        supplierList.forEach(t -> t.setPriority(9));

        bakerList.forEach(Thread::start);
        supplierList.forEach(Thread::start);

        int bakersAlive = attributes.getBakersAmount();
        int suppliersAlive = attributes.getSuppliersAmount();

        while (bakersAlive > 0 || suppliersAlive > 0) {
            if (bakerList.stream().anyMatch(Baker::isDone)) {
                bakersAlive--;
            }
            if (supplierList.stream().anyMatch(Supplier::isDone)) {
                suppliersAlive--;
            }
        }

        System.out.println("Pizzeria is closed.");
        System.exit(0);
    }

    public PizzeriaAttributes getAttributes() {
        return attributes;
    }

    public Deque<Order> getOrdersQueue() {
        return ordersQueue;
    }

    public Deque<Order> getStorageQueue() {
        return storageQueue;
    }
}
