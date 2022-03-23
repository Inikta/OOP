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
        currentOrder = new Order();
        do {
            if (!orderQueue.isEmpty() & !currentOrder.isInQueue()) {
                synchronized (orderQueue) {
                    currentOrder = orderQueue.pop();
                    System.out.println(currentOrder.toString());
                    orderQueue.notifyAll();
                }
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                makePizza();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                if (storageQueue.size() < pizzeria.getStorageSize()) {
                    synchronized (storageQueue) {
                        pushToStorage();
                        System.out.println(currentOrder.toString());
                        storageQueue.notifyAll();
                    }
                } else {
                    wait();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!currentOrder.isEndWork());
    }

    private void makePizza() throws InterruptedException {
        inWork = true;
        Thread.sleep(bakeTime);
        currentOrder.setInQueue(true);
    }

    public void pushToStorage() throws InterruptedException {
        currentOrder.setInStorage(true);
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
