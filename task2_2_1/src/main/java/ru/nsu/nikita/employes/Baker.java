package ru.nsu.nikita.employes;

import ru.nsu.nikita.Order;
import ru.nsu.nikita.Pizzeria;

import java.util.Deque;

public class Baker extends Thread {

    private final int bakeTime;
    private final Deque<Order> orderQueue;
    private final Deque<Order> storageQueue;
    private final Pizzeria pizzeria;
    private final int number;
    private boolean inWork;
    private Order currentOrder;

    public Baker(int number,
                 int bakeTime,
                 Pizzeria pizzeria) {
        this.number = number;
        this.pizzeria = pizzeria;
        this.bakeTime = bakeTime;
        inWork = false;

        this.orderQueue = pizzeria.getOrdersQueue();
        this.storageQueue = pizzeria.getStorageQueue();
    }

    @Override
    public void run() {
        currentOrder = new Order();
        do {
            //take order from queue
            //and make it, if bakery is not filled with pizza in production/ready
            if (!orderQueue.isEmpty() & !currentOrder.isInBakery()) {
                takeOrder();
                //end work, if new order was actually a signal to end
                if (currentOrder.isEndWork()) {
                    break;
                }
                try {
                    makePizza();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            //push ready pizza to storage if storage has place for it
            } else if (currentOrder.isInBakery() & storageQueue.size() < pizzeria.getStorageLimit()) {
                try {
                    pushToStorage();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            //wait until storage will have place for ready pizza
            //or order for new pizza appears
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        //end work, if new order was actually a signal to end
        } while (!currentOrder.isEndWork());
    }

    private synchronized void takeOrder() {
        currentOrder = orderQueue.pop();
        System.out.println(currentOrder.toString());
        orderQueue.notifyAll();
    }

    private void makePizza() throws InterruptedException {
        inWork = true;
        Thread.sleep(bakeTime);
        currentOrder.setInBakery(true);
    }

    public synchronized void pushToStorage() throws InterruptedException {
        currentOrder.setInStorage(true);
        currentOrder.setInBakery(false);
        storageQueue.addLast(currentOrder);
        inWork = false;

        System.out.println(currentOrder.toString());
        storageQueue.notifyAll();
    }

    public boolean isDone() {
        return inWork;
    }

    public void setDone(boolean done) {
        this.inWork = done;
    }

    public int getBakeTime() {
        return bakeTime;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public int getNumber() {
        return number;
    }
}
