package ru.nsu.nikita.employes;

import ru.nsu.nikita.Order;
import ru.nsu.nikita.Pizzeria;

import java.util.Deque;

public class Baker extends Thread {

    private final int bakeTime;
    private final Deque<Order> orderQueue;
    private final Deque<Order> storageQueue;
    private final Pizzeria pizzeria;
    private final int number;
    private boolean inWork;
    private Order currentOrder;

    public Baker(int number,
                 int bakeTime,
                 Pizzeria pizzeria) {
        this.number = number;
        this.pizzeria = pizzeria;
        this.bakeTime = bakeTime;
        inWork = false;

        this.orderQueue = pizzeria.getOrdersQueue();
        this.storageQueue = pizzeria.getStorageQueue();
    }

    @Override
    public void run() {
        while (!currentOrder.isEndWork()) {
            synchronized (orderQueue) {
                currentOrder = orderQueue.pop();
            }
            orderQueue.notifyAll();

            try {
                makePizza();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                synchronized (storageQueue) {
                    if (storageQueue.size() < pizzeria.getStorageSize()) {
                        pushToStorage();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            storageQueue.notifyAll();
        }
    }

    private void makePizza() throws InterruptedException {
        inWork = true;
        wait(bakeTime);
        currentOrder.setReady(true);
    }

    public void pushToStorage() throws InterruptedException {
        storageQueue.push(currentOrder);
        inWork = false;
    }

    public boolean isDone() {
        return inWork;
    }

    public void setDone(boolean done) {
        this.inWork = done;
    }

    public int getBakeTime() {
        return bakeTime;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public int getNumber() {
        return number;
    }
}
