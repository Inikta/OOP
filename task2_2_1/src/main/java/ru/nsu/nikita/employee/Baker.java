package ru.nsu.nikita.employee;

import ru.nsu.nikita.Pizzeria;
import ru.nsu.nikita.order_generators.Order;

public class Baker extends Thread {

    private final Pizzeria pizzeria;
    private final int number;
    private final int bakeTime;

    private Order currentOrder;

    public Baker(BakerAttributes bakerAttributes, Pizzeria pizzeria) {
        this.number = bakerAttributes.getNumber();
        this.bakeTime = bakerAttributes.getBakeTime();
        this.pizzeria = pizzeria;
        currentOrder = new Order();
    }

    @Override
    public void run() {
        while (!currentOrder.isEndWork()) {

            takeOrder();

            try {
                makePizza();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                pushToStorage();
                if (currentOrder.isEndWork()) {
                    System.out.println("Baker #" + number + ": the work is over.");
                }
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void takeOrder() {
        synchronized (pizzeria.getOrdersQueue()) {
            if (!pizzeria.getOrdersQueue().isEmpty() & !currentOrder.isInBakery()) {
                currentOrder = pizzeria.getOrdersQueue().pop();
                currentOrder.setInBakery(true);
                currentOrder.setInWork(true);
                synchronized (System.out) {
                    System.out.println("Taken: Baker #" + number + " - order №" + currentOrder.getNumber());
                }
                pizzeria.getOrdersQueue().notifyAll();
            }
        }
    }

    private void makePizza() throws InterruptedException {
        if (!currentOrder.isEndWork() & currentOrder.isInWork()) {
            Thread.sleep(bakeTime);
            currentOrder.setInWork(false);
            currentOrder.setReady(true);
            synchronized (System.out) {
                System.out.println("Ready: Baker #" + number + " - order №" + currentOrder.getNumber());
            }
        } else if (currentOrder.isEndWork()) {
            currentOrder.setInWork(false);
            currentOrder.setReady(true);
        }
    }

    private void pushToStorage() throws InterruptedException {
        synchronized (pizzeria.getStorageQueue()) {
            if (pizzeria.getStorageQueue().size() < pizzeria.getStorageLimit()
                    & currentOrder.isReady()) {
                currentOrder.setInBakery(false);
                currentOrder.setInStorage(true);
                pizzeria.getStorageQueue().push(currentOrder);
                synchronized (System.out) {
                    System.out.println("In storage: Baker #" + number + " - order №" + currentOrder.getNumber());
                }
                pizzeria.getStorageQueue().notifyAll();
                if (currentOrder.isEndWork()) {
                    return;
                }
            } else {
                pizzeria.getStorageQueue().wait();
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
