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

    private final Deque<Order> bag;
    private Order lastOrder;
    private Order lastRemovedOrder;


    public Supplier(SupplierAttributes supplierAttributes, Pizzeria pizzeria) {
        this.number = supplierAttributes.getNumber();
        this.bagLimit = supplierAttributes.getBagLimit();
        this.deliveryTime = supplierAttributes.getDeliveryTime();
        this.pizzeria = pizzeria;

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
                }
            }
        } while (!lastRemovedOrder.isEndWork());
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

    @Override
    public String toString() {
        return "Supplier #" + number + " {" +
                "\n\tBag: " + bag.size() + "/" + bagLimit +
                "\n\tbag=" + bag +
                "\n\tdeliveryTime=" + deliveryTime +
                "\n\tlastOrder=" + lastOrder +
                "\n\tlastRemovedOrder=" + lastRemovedOrder +
                '}';
    }
}
