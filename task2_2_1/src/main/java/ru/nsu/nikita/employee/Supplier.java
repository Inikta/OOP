package ru.nsu.nikita.employee;

import ru.nsu.nikita.Pizzeria;
import ru.nsu.nikita.order_generators.Order;

import java.util.ArrayDeque;
import java.util.Deque;

public class Supplier extends Thread {

    private final Pizzeria pizzeria;
    private final int number;
    private final int bagLimit;
    private final int deliveryTime;
    private final int waitingTime;
    private boolean done;

    private final Deque<Order> bag;
    private Order lastOrder;
    private Order lastRemovedOrder;

    /**
     * Supplier thread, which waits for orders in the storage, takes them and delivers.
     * If another order is endWork, then will deliver remaining orders in the bag and end work.
     * @param supplierAttributes parameters of this supplier.
     * @param pizzeria pizzeria, to which this supplier belongs.
     */
    public Supplier(SupplierAttributes supplierAttributes, Pizzeria pizzeria) {
        this.number = supplierAttributes.getNumber();
        this.bagLimit = supplierAttributes.getBagLimit();
        this.deliveryTime = supplierAttributes.getDeliveryTime();
        this.waitingTime = supplierAttributes.getWaitingTime();
        this.pizzeria = pizzeria;
        this.done = false;

        bag = new ArrayDeque<>(bagLimit);
        lastOrder = new Order();
        lastRemovedOrder = new Order();
    }

    @Override
    public void run() {
        do {
            try {
                takePizza();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (!bag.isEmpty()) {
                try {
                    deliverPizza();
                } catch (InterruptedException e) {
                    System.out.println("Supplier #" + number + ": the work is over.");
                    done = true;
                }
            }
        } while (!done);
    }

    private void takePizza() throws InterruptedException {
        synchronized (pizzeria.getStorageQueue()) {
            while (bag.size() < bagLimit) {

                if (!pizzeria.getStorageQueue().isEmpty()) {
                    if (!pizzeria.getStorageQueue().getFirst().isEndWork()) {
                        lastOrder = pizzeria.getStorageQueue().pop();
                    } else {
                        lastOrder = pizzeria.getStorageQueue().getFirst();
                    }
                    bag.addLast(lastOrder);
                    lastOrder.setInStorage(false);
                    lastOrder.setInBag(true);
                    synchronized (System.out) {
                        System.out.println("In bag " + bag.size() + "/" + bagLimit + " :" + "Supplier #" + number + ": " + lastOrder.getNumber());
                    }
                    pizzeria.getStorageQueue().notifyAll();
                }
                if ((pizzeria.getStorageQueue().isEmpty() & !bag.isEmpty()) || lastOrder.isEndWork()) {
                    pizzeria.getStorageQueue().wait(waitingTime);
                    return;
                }

                pizzeria.getStorageQueue().wait();
            }
        }
    }

    private void deliverPizza() throws InterruptedException {
        lastRemovedOrder = bag.pop();
        if (lastRemovedOrder.isEndWork()) {
            this.interrupt();
        }
        Thread.sleep(deliveryTime);
        lastRemovedOrder.setInBag(false);
        lastRemovedOrder.setDelivered(true);
        synchronized (System.out) {
            System.out.println("Delivered " + bag.size() + "/" + bagLimit + " :" + "Supplier #" + number + ": " + lastRemovedOrder.getNumber());
        }
    }

    public Pizzeria getPizzeria() {
        return pizzeria;
    }

    public int getNumber() {
        return number;
    }

    public int getBagLimit() {
        return bagLimit;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public Deque<Order> getBag() {
        return bag;
    }

    public Order getLastOrder() {
        return lastOrder;
    }

    public Order getLastRemovedOrder() {
        return lastRemovedOrder;
    }

    public int getWaitingTime() {return waitingTime;}

    public boolean isDone() {
        return done;
    }

    @Override
    public String toString() {
        return "Supplier #" + number + " {" +
                "\n\tBag: " + bag.size() + "/" + bagLimit +
                "\n\tbag=" + bag +
                "\n\tdeliveryTime=" + deliveryTime +
                "\n\tlastOrder=" + lastOrder +
                "\n\twaitingTime=" + waitingTime +
                "\n\tlastRemovedOrder=" + lastRemovedOrder +
                '}';
    }
}
