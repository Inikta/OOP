package ru.nsu.nikita.employes;

import ru.nsu.nikita.Order;
import ru.nsu.nikita.Pizzeria;

import java.util.ArrayList;
import java.util.Deque;

public class Supplier extends Thread {
    private final int deliveryTime;
    private final int maxBagSize;
    private final Deque<Order> storageQueue;
    private final int number;
    private int bagFilling;
    private Order lastOrder;
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
        lastOrder = new Order();
        do {
            //take pizza from storage while it is not empty or bag is full
            //or last taken order was to end work
            while (bagFilling < maxBagSize) {
                try {
                    boolean stop = takePizza();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            storageQueue.notifyAll();

            //deliver all pizza
            for (Order pizza : bag) {
                try {
                    if (!pizza.isEndWork()) {
                        deliverPizza(pizza);
                        System.out.println(pizza);
                    } else {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } while (!lastOrder.isEndWork());
    }

    private synchronized boolean takePizza() throws InterruptedException {
        if (!storageQueue.isEmpty()) {
            lastOrder = storageQueue.pop();
            System.out.println(lastOrder.toString());
            bag.add(lastOrder);
            lastOrder.setInStorage(false);

            bagFilling++;
            System.out.println(lastOrder.toString());
            if (lastOrder.isEndWork()) {
                return true;
            }
        } else {
            this.wait();
        }

        return false;
    }

    private void deliverPizza(Order pizza) throws InterruptedException {
        Thread.sleep(deliveryTime);
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
