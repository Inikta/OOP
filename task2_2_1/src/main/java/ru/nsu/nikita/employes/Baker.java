package ru.nsu.nikita.employes;

import ru.nsu.nikita.Order;

import java.util.concurrent.BlockingQueue;

public class Baker implements Runnable {

    private final int bakeTime;
    private final BlockingQueue<Order> orderQueue;
    private final BlockingQueue<Order> storageQueue;
    private boolean done;
    private Order currentOrder;

    public Baker(int bakeTime,
                 BlockingQueue<Order> orderQueue,
                 BlockingQueue<Order> storageQueue) {
        this.bakeTime = bakeTime;
        done = false;

        this.orderQueue = orderQueue;
        this.storageQueue = storageQueue;
    }

    @Override
    public void run() {
        while (!currentOrder.isEndWork()) {
            try {
                makePizza();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                pushToStorage();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setDone(true);
        }
    }

    private void makePizza() throws InterruptedException {
        currentOrder = orderQueue.take();
        wait(bakeTime);
        currentOrder.setReady(true);
    }

    public void pushToStorage() throws InterruptedException {
        if (storageQueue.remainingCapacity() > 0) {
            storageQueue.put(currentOrder);
            done = true;
        }
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
