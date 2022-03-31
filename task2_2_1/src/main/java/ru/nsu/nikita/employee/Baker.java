package ru.nsu.nikita.employee;

import ru.nsu.nikita.Pizzeria;
import ru.nsu.nikita.order_generators.Order;

public class Baker extends Thread {

    private final Pizzeria pizzeria;
    private final int number;
    private final int bakeTime;
    private boolean done;

    private Order currentOrder;

    /**
     * Baker thread, which takes orders, makes them and pushes to the storage if it is not full.
     * If storage is full, then will wait until one place for order will be freed.
     * If order is endWork, then will push it to the storage and end work.
     * @param bakerAttributes parameters of this baker.
     * @param pizzeria pizzeria, to which this baker belongs.
     */
    public Baker(BakerAttributes bakerAttributes, Pizzeria pizzeria) {
        this.number = bakerAttributes.getNumber();
        this.bakeTime = bakerAttributes.getBakeTime();
        this.pizzeria = pizzeria;
        this.done = false;
        currentOrder = new Order();
    }

    @Override
    public void run() {
        while (!done) {

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
                    done = true;
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
            if (pizzeria.getStorageQueue().size() < pizzeria.getAttributes().getStorageLimit()
                    & currentOrder.isReady()) {
                currentOrder.setInBakery(false);
                currentOrder.setInStorage(true);
                pizzeria.getStorageQueue().push(currentOrder);
                synchronized (System.out) {
                    System.out.println("In storage: Baker #" + number + " - order №" + currentOrder.getNumber());
                }
                pizzeria.getStorageQueue().notifyAll();
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

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return "Baker #" + number + " {" +
                "\n\tcurrentOrder:\n" + currentOrder.toString() +
                '}';
    }
}
