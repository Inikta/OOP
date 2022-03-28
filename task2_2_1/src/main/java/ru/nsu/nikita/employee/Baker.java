package ru.nsu.nikita.employee;

import ru.nsu.nikita.Pizzeria;
import ru.nsu.nikita.order_generators.Order;

public class Baker extends Thread {

    private final Pizzeria pizzeria;
    private final int number;
    private final int bakeTime;

    private Order currentOrder;

    public Baker(int number, int bakeTime, Pizzeria pizzeria) {
        this.number = number;
        this.bakeTime = bakeTime;
        this.pizzeria = pizzeria;
        currentOrder = new Order();
    }

    @Override
    public void run() {
        while (!currentOrder.isEndWork()) {
            try {
                takeOrder();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
        }
    }

    private void takeOrder() throws InterruptedException {
        synchronized (pizzeria.getOrdersQueue()) {
            if (!pizzeria.getOrdersQueue().isEmpty() & !currentOrder.isInBakery()) {
                currentOrder = pizzeria.getOrdersQueue().pop();
                currentOrder.setInBakery(true);
                currentOrder.setInWork(true);
                System.out.println("Baker #" + number + ": " + currentOrder.toString());
                pizzeria.getOrdersQueue().notifyAll();
                //wait();
            }
        }
    }

    private void makePizza() throws InterruptedException {
        if (!currentOrder.isEndWork() & currentOrder.isInWork()) {
            Thread.sleep(bakeTime);
            currentOrder.setInWork(false);
            currentOrder.setReady(true);
            System.out.println("Baker #" + number + ": " + currentOrder.toString());
        }
    }

    private void pushToStorage() throws InterruptedException {
        synchronized (pizzeria.getStorageQueue()) {
            if (!currentOrder.isEndWork()
                    & pizzeria.getStorageQueue().size() < pizzeria.getStorageLimit()
                    & currentOrder.isReady()) {
                currentOrder.setInBakery(false);
                currentOrder.setInStorage(true);
                pizzeria.getStorageQueue().push(currentOrder);
                System.out.println("Baker #" + number + ": " + currentOrder.toString());
                pizzeria.getStorageQueue().notifyAll();
                //wait();
            }
        }
    }

    public int getNumber() {
        return number;
    }

    public int getBakeTime() {
        return bakeTime;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    @Override
    public String toString() {
        return "Baker #" + number + " {" +
                "\n\tcurrentOrder:\n" + currentOrder.toString() +
                '}';
    }
}
