package ru.nsu.nikita;

import ru.nsu.nikita.employes.Baker;
import ru.nsu.nikita.employes.Supplier;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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

    private Deque<Order> ordersQueue;
    private Deque<Order> storageQueue;

    private int ordersCap;
    private int storageSize;

    private OrdersGenerators.DynamicGenerator generator;

    public Pizzeria(int bakersAmount,
                    int suppliersAmount,
                    int storageSize,
                    int ordersCap,
                    int bakeTime,
                    int deliveryTime,
                    int bagSize) {
        this.ordersCap = ordersCap;
        this.storageSize = storageSize;

        ordersQueue = new ArrayDeque<>(ordersCap);
        storageQueue = new ArrayDeque<>(storageSize);

        this.bakersAmount = bakersAmount;
        freeBakers = this.bakersAmount;
        setBakeTime(bakeTime);

        bakersList = new ArrayList<>(this.bakersAmount);
        for (int i = 0; i < bakersAmount; i++) {
            bakersList.add(new Baker(i, this.bakeTime, this));
        }
        bakersList.forEach(b -> b.setPriority(5));

        this.suppliersAmount = suppliersAmount;
        freeSuppliers = this.suppliersAmount;
        setDeliveryTime(deliveryTime);
        setBagSize(bagSize);

        suppliersList = new ArrayList<>(suppliersAmount);
        for (int i = 0; i < bakersAmount; i++) {
            suppliersList.add(new Supplier(i, deliveryTime, this.bagSize, this));
        }
        suppliersList.forEach(s -> s.setPriority(7));
    }

    public void makeOrders(int mode) {
        if (mode == 0) {
            new OrdersGenerators.StaticGenerator(ordersCap, ordersQueue).addOrders();
            for (int i = 0; i < bakersAmount; i++) {
                ordersQueue.add(new Order(true));
            }
        } else if (mode == 1) {
            generator = new OrdersGenerators.DynamicGenerator(30, 1000, ordersQueue);
            generator.setPriority(7);
            generator.start();
        }
    }

    public void startWork(int mode) {
        makeOrders(mode);
        bakersList.forEach(Thread::start);
        suppliersList.forEach(Thread::start);
    }

    public void roughEndWork() {
        generator.interrupt();
        for (Baker b : bakersList) {
            b.interrupt();
        }
        for (Supplier s : suppliersList) {
            s.interrupt();
        }
    }

    /*public void endWork() {
        generator.setEndWork(true);
        generator.interrupt();
        for (int i = 0; i < bakersAmount; i++) {
            ordersQueue.addFirst(new Order(true));
        }

        for (int i = 0; i < bakersAmount; i++) {
            storageQueue.addFirst(new Order(true));
        }
    }*/

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

    public int getBakersAmount() {
        return bakersAmount;
    }

    public int getFreeBakers() {
        return freeBakers;
    }

    public void setFreeBakers(int freeBakers) {
        this.freeBakers = freeBakers;
    }

    public List<Baker> getBakersList() {
        return bakersList;
    }

    public void setBakersList(List<Baker> bakersList) {
        this.bakersList = bakersList;
    }

    public int getSuppliersAmount() {
        return suppliersAmount;
    }

    public int getFreeSuppliers() {
        return freeSuppliers;
    }

    public void setFreeSuppliers(int freeSuppliers) {
        this.freeSuppliers = freeSuppliers;
    }

    public List<Supplier> getSuppliersList() {
        return suppliersList;
    }

    public void setSuppliersList(List<Supplier> suppliersList) {
        this.suppliersList = suppliersList;
    }

    public Deque<Order> getOrdersQueue() {
        return ordersQueue;
    }

    public void setOrdersQueue(Deque<Order> ordersQueue) {
        this.ordersQueue = ordersQueue;
    }

    public Deque<Order> getStorageQueue() {
        return storageQueue;
    }

    public void setStorageQueue(Deque<Order> storageQueue) {
        this.storageQueue = storageQueue;
    }

    public int getOrdersCap() {
        return ordersCap;
    }

    public void setOrdersCap(int ordersCap) {
        this.ordersCap = ordersCap;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public void setStorageSize(int storageSize) {
        this.storageSize = storageSize;
    }
}
