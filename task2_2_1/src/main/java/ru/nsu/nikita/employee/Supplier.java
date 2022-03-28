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
    public void run() {
        do {
            while (bag.size() < bagLimit) {
                try {
                    takePizza();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(this);
            }

            while (!bag.isEmpty()) {
                System.out.println(this);
                try {
                    deliverPizza();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (!lastRemovedOrder.isEndWork());
    }

    private void takePizza() throws InterruptedException {
        synchronized (pizzeria.getStorageQueue()) {
            if (!pizzeria.getStorageQueue().isEmpty()) {
                lastOrder = pizzeria.getStorageQueue().pop();
                bag.addLast(lastOrder);
                lastOrder.setInStorage(false);
                lastOrder.setInBag(true);
                System.out.println("Supplier #" + number + ": " + lastOrder.toString());
                pizzeria.getStorageQueue().notifyAll();
                wait();
            } else {
                wait();
            }
            System.out.println("Supplier #" + number + ": " + lastOrder.toString());
        }
    }

    private void deliverPizza() throws InterruptedException {
        Thread.sleep(deliveryTime);
        lastRemovedOrder = bag.pop();
        lastRemovedOrder.setInBag(false);
        lastRemovedOrder.setDelivered(true);
        System.out.println("Supplier #" + number + ": " + lastRemovedOrder.toString());
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
