package ru.nsu.nikita;

import java.util.Deque;
import java.util.Scanner;

public class OrdersGenerators {

    public static class StaticGenerator {

        private final int cap;
        private final Deque<Order> queue;
        private boolean endWork;
        private int counter;

        public StaticGenerator(int cap, Pizzeria pizzeria) {
            this.cap = cap;
            counter = 0;
            queue = pizzeria.getOrdersQueue();
            setEndWork(false);
        }

        public void addOrders() {
            for (counter = 0; counter < cap; counter++) {
                queue.add(new Order(counter));
            }
        }

        public boolean isEndWork() {
            return endWork;
        }

        public void setEndWork(boolean endWork) {
            this.endWork = endWork;
        }
    }

    public static class DynamicGenerator extends Thread {
        private final int delay;
        private final int cap;
        private final Deque<Order> queue;
        private int counter;
        private boolean endWork;

        public DynamicGenerator(int delay, int cap, Pizzeria pizzeria) {
            this.delay = delay;
            this.cap = cap;
            this.queue = pizzeria.getOrdersQueue();
            this.counter = 0;
            setEndWork(false);
        }

        @Override
        public void run() {
            while (counter < cap) {
                try {
                    synchronized (queue) {
                        queue.push(new Order(counter++));
                        wait(delay);
                    }
                } catch (InterruptedException e) {
                    if (endWork) {
                        System.out.println("Day is over. No more orders.");
                        break;
                    } else {
                        e.printStackTrace();
                    }
                }
            }
        }

        public boolean isEndWork() {
            return endWork;
        }

        public void setEndWork(boolean endWork) {
            this.endWork = endWork;
        }
    }

    public static class ManualOrdering extends Thread {

        private final Pizzeria pizzeria;

        public ManualOrdering(Pizzeria pizzeria) {
            this.pizzeria = pizzeria;
        }

        @Override
        public void run() {
            boolean stop = false;
            Scanner in = new Scanner(System.in);
            int orderNum = 0;
            do {
                int input = in.nextInt();
                switch (input) {
                    case 0 -> {
                        System.out.println(
                                "Help:\n" +
                                        "11 - add one new order\n" +
                                        "12 X - add X new orders\n" +
                                        "200 - \"kill\" one baker\n" +
                                        "201 X - \"kill\" X bakers\n" +
                                        "300 - \"kill\" one supplier\n" +
                                        "301 X - \"kill\" X suppliers\n" +
                                        "666 - \"kill\" all bakers, suppliers and end work\n");
                    }
                    case 666 -> {
                        synchronized (pizzeria.getOrdersQueue()) {
                            for (int i = 0; i < pizzeria.getBakersAmount(); i++) {
                                pizzeria.getOrdersQueue().addLast(new Order(true));
                            }
                        }
                        synchronized (pizzeria.getStorageQueue()) {
                            for (int i = 0; i < pizzeria.getSuppliersAmount(); i++) {
                                pizzeria.getStorageQueue().addLast(new Order(true));
                            }
                        }
                        notifyAll();
                        stop = true;
                    }
                    case 11 -> {
                        synchronized (pizzeria.getOrdersQueue()) {
                            pizzeria.getOrdersQueue().addLast(new Order(orderNum++));
                        }
                        notifyAll();
                    }
                    case 12 -> {
                        int parameter = in.nextInt();
                        synchronized (pizzeria.getOrdersQueue()) {
                            for (int i = 0; i < parameter; i++) {
                                pizzeria.getOrdersQueue().addLast(new Order(orderNum++));
                            }
                        }
                        notifyAll();
                    }
                    case 200 -> {
                        synchronized (pizzeria.getOrdersQueue()) {
                            pizzeria.getOrdersQueue().addLast(new Order(true));
                        }
                        notifyAll();
                    }
                    case 201 -> {
                        int parameter = in.nextInt();
                        synchronized (pizzeria.getOrdersQueue()) {
                            for (int i = 0; (i < parameter & i < pizzeria.getBakersAmount()); i++) {
                                pizzeria.getOrdersQueue().addLast(new Order(true));
                            }
                        }
                        notifyAll();
                    }
                    case 300 -> {
                        synchronized (pizzeria.getStorageQueue()) {
                            pizzeria.getStorageQueue().addLast(new Order(true));
                        }
                        notifyAll();
                    }
                    case 301 -> {
                        int parameter = in.nextInt();
                        synchronized (pizzeria.getStorageQueue()) {
                            for (int i = 0; (i < parameter & i < pizzeria.getSuppliersAmount()); i++) {
                                pizzeria.getStorageQueue().addLast(new Order(true));
                            }
                        }
                        notifyAll();
                    }
                }
            } while (!stop);
        }
    }
}
