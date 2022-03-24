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


    public Supplier(int number, int bagLimit, int deliveryTime, Pizzeria pizzeria) {
        this.number = number;
        this.bagLimit = bagLimit;
        this.deliveryTime = deliveryTime;
        this.pizzeria = pizzeria;

        bag = new ArrayDeque<>(bagLimit);
        lastOrder = new Order();
        lastRemovedOrder = new Order();
    }

    @Override
    public synchronized void run() {
        do {
            do {
                if (!pizzeria.getStorageQueue().isEmpty()) {
                    takePizza();
                } else if (bag.isEmpty()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } while (
                    (bag.size() < bagLimit & !pizzeria.getStorageQueue().isEmpty())
                            || bag.isEmpty()
                            || !lastOrder.isEndWork());

            notifyAll();

            do {
                try {
                    deliverPizza();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (!bag.isEmpty());

        } while (!lastRemovedOrder.isEndWork());
    }

    private void takePizza() {
        lastOrder = pizzeria.getStorageQueue().getFirst();
        bag.addLast(lastOrder);
        lastOrder.setInStorage(false);
        lastOrder.setInBag(true);
    }

    private void deliverPizza() throws InterruptedException {
        Thread.sleep(deliveryTime);
        lastRemovedOrder = bag.removeFirst();
        lastRemovedOrder.setInBag(false);
        lastRemovedOrder.setDelivered(true);
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


}
