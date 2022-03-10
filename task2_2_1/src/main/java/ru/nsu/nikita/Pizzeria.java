package ru.nsu.nikita;

import ru.nsu.nikita.employes.Baker;
import ru.nsu.nikita.employes.Supplier;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Pizzeria {

    private final int bakersAmount;
    private int freeBakers;
    private List<Baker> bakersList;

    private final int suppliersAmount;
    private int freeSuppliers;
    private List<Supplier> suppliersList;

    private int bakeTime;
    private int deliveryTime;
    private int bagSize;

    private BlockingQueue<Order> ordersQueue;
    private BlockingQueue<Order> storageQueue;

    private int ordersCap;

    public Pizzeria(int bakersAmount,
                    int suppliersAmount,
                    int storageSize,
                    int ordersCap) {
        this.ordersCap = ordersCap;

        ordersQueue = new LinkedBlockingQueue<>();
        storageQueue = new LinkedBlockingQueue<>(storageSize);

        this.bakersAmount = bakersAmount;
        freeBakers = this.bakersAmount;
        setBakeTime(30);
        for (int i = 0; i < bakersAmount; i++) {
            bakersList.add(new Baker(bakeTime, ordersQueue, storageQueue));
        }

        this.suppliersAmount = suppliersAmount;
        freeSuppliers = this.suppliersAmount;
        setDeliveryTime(80);
        setBagSize(3);
        for (int i = 0; i < bakersAmount; i++) {
            suppliersList.add(new Supplier(deliveryTime, bagSize, storageQueue));
        }
    }

    public Pizzeria(int bakersAmount,
                    int suppliersAmount,
                    int storageSize) {
        ordersQueue = new LinkedBlockingQueue<>();
        storageQueue = new LinkedBlockingQueue<>(storageSize);

        this.bakersAmount = bakersAmount;
        freeBakers = this.bakersAmount;
        setBakeTime(30);
        for (int i = 0; i < bakersAmount; i++) {
            bakersList.add(new Baker(bakeTime, ordersQueue, storageQueue));
        }

        this.suppliersAmount = suppliersAmount;
        freeSuppliers = this.suppliersAmount;
        setDeliveryTime(80);
        setBagSize(3);
        for (int i = 0; i < bakersAmount; i++) {
            suppliersList.add(new Supplier(deliveryTime, bagSize, storageQueue));
        }
    }

    public void startWork(int mode) {
        if (mode == 0) {
            new OrdersGenerators.StaticGenerator(ordersCap, ordersQueue).addOrders();
        } else if (mode == 1) {
            new OrdersGenerators.DynamicGenerator(30, 1000, ordersQueue);
        }

    }

    public int getBakeTime() {
        return bakeTime;
    }

    public void setBakeTime(int bakeTime) {
        this.bakeTime = bakeTime;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getBagSize() {
        return bagSize;
    }

    public void setBagSize(int bagSize) {
        this.bagSize = bagSize;
    }
}
