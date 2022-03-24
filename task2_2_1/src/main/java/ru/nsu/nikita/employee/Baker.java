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
    public synchronized void run() {
        while (true) {
            if (!pizzeria.getOrdersQueue().isEmpty()) {
                takeOrder();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            notifyAll();

            if (currentOrder.isEndWork()) {
                break;
            }

            try {
                makePizza();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            do {
                if (pizzeria.getStorageQueue().size() < pizzeria.getStorageLimit()) {
                    pushToStorage();
                    notifyAll();
                } else {
                    if (currentOrder.isReady()) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            } while (!currentOrder.isInStorage());
        }
    }

    private void takeOrder() {
        currentOrder = pizzeria.getOrdersQueue().pop();
        currentOrder.setInWork(true);
    }

    private void makePizza() throws InterruptedException {
        if (!currentOrder.isEndWork()) {
            Thread.sleep(bakeTime);
            currentOrder.setInWork(false);
            currentOrder.setReady(true);
        }
    }

    private void pushToStorage() {
        if (!currentOrder.isEndWork()) {
            pizzeria.getStorageQueue().addLast(currentOrder);
            currentOrder.setInStorage(true);
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
        return "Baker{" +
                "number=" + number +
                ", currentOrder=" + currentOrder +
                '}';
    }
}
