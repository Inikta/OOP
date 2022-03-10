package ru.nsu.nikita.employes;

import ru.nsu.nikita.Order;

import java.util.concurrent.BlockingQueue;

public class Supplier implements Runnable {
    private final int deliveryTime;
    private final int maxBagSize;
    private final BlockingQueue<Order> storageQueue;
    private int bagFilling;
    private Order lastOrder;

    public Supplier(int deliveryTime,
                    int maxBagSize,
                    BlockingQueue<Order> storageQueue) {
        this.deliveryTime = deliveryTime;
        this.maxBagSize = maxBagSize;
        this.storageQueue = storageQueue;
    }

    @Override
    public void run() {
        while (!lastOrder.isEndWork()) {
            while (bagFilling < maxBagSize && !storageQueue.isEmpty()) {
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
        lastOrder = storageQueue.take();
        bagFilling++;
    }

    private void deliverPizza() throws InterruptedException {
        wait(deliveryTime);
        bagFilling--;
    }
}
