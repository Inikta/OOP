package ru.nsu.nikita.employes;

import ru.nsu.nikita.Order;
import ru.nsu.nikita.Pizzeria;

import java.util.ArrayList;
import java.util.Deque;

public class Supplier extends Thread {
    private final int deliveryTime;
    private final int maxBagSize;
    private final Deque<Order> storageQueue;
    private int bagFilling;
    private Order lastOrder;

    private final int number;

    private ArrayList<Order> bag;

    public Supplier(int number,
                    int deliveryTime,
                    int maxBagSize,
                    Pizzeria pizzeria) {
        this.number = number;
        this.deliveryTime = deliveryTime;
        this.maxBagSize = maxBagSize;
        this.storageQueue = pizzeria.getStorageQueue();
        this.bag = new ArrayList<>(maxBagSize);
    }

    @Override
    public void run() {
        while (true) {
            while (bagFilling < maxBagSize && !storageQueue.isEmpty()) {
                try {
                    takePizza();
                    System.out.println(lastOrder.toString());
                    storageQueue.notifyAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (lastOrder.isEndWork()) {
                    break;
                }
            }

            for (Order pizza : bag) {
                try {
                    deliverPizza(pizza);
                    System.out.println(pizza);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void takePizza() throws InterruptedException {
        synchronized (storageQueue) {
            lastOrder = storageQueue.pop();
            System.out.println(lastOrder.toString());
        }
        bag.add(lastOrder);
        lastOrder.setInStorage(false);

        bagFilling++;
    }

    private void deliverPizza(Order pizza) throws InterruptedException {
        wait(deliveryTime);
        pizza.setDelivered(true);
        bag.remove(pizza);
        bagFilling--;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "maxBagSize=" + maxBagSize +
                ", bagFilling=" + bagFilling +
                ", lastOrder=" + lastOrder +
                '}';
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public int getMaxBagSize() {
        return maxBagSize;
    }

    public int getBagFilling() {
        return bagFilling;
    }

    public void setBagFilling(int bagFilling) {
        this.bagFilling = bagFilling;
    }

    public Order getLastOrder() {
        return lastOrder;
    }

    public void setLastOrder(Order lastOrder) {
        this.lastOrder = lastOrder;
    }

    public int getNumber() {
        return number;
    }
}
