package ru.nsu.nikita.employes;

import ru.nsu.nikita.Order;
import ru.nsu.nikita.Pizzeria;

import java.util.Deque;

public class Supplier extends Thread {
    private final int deliveryTime;
    private final int maxBagSize;
    private final Deque<Order> storageQueue;
    private int bagFilling;
    private Order lastOrder;

    private final int number;

    public Supplier(int number,
                    int deliveryTime,
                    int maxBagSize,
                    Pizzeria pizzeria) {
        this.number = number;
        this.deliveryTime = deliveryTime;
        this.maxBagSize = maxBagSize;
        this.storageQueue = pizzeria.getStorageQueue();
    }

    @Override
    public void run() {
        while (!lastOrder.isEndWork()) {
            while (bagFilling < maxBagSize && !storageQueue.isEmpty()) {
                synchronized (storageQueue) {
                    lastOrder = storageQueue.pop();
                }
                storageQueue.notifyAll();

                try {
                    takePizza();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (bagFilling > 0) {
                try {
                    deliverPizza();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void takePizza() throws InterruptedException {
        bagFilling++;
    }

    private void deliverPizza() throws InterruptedException {
        wait(deliveryTime);
        bagFilling--;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "maxBagSize=" + maxBagSize +
                ", bagFilling=" + bagFilling +
                ", lastOrder=" + lastOrder +
                '}';
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public int getMaxBagSize() {
        return maxBagSize;
    }

    public int getBagFilling() {
        return bagFilling;
    }

    public void setBagFilling(int bagFilling) {
        this.bagFilling = bagFilling;
    }

    public Order getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(Order lastOrder) {
        this.lastOrder = lastOrder;
    }

    public int getNumber() {
        return number;
    }
}
