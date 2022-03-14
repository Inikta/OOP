package ru.nsu.nikita;

import java.util.Deque;

public class OrdersGenerators {

    public static class StaticGenerator {

        private final int cap;
        private boolean endWork;
        private int counter;
        private final Deque<Order> queue;

        public StaticGenerator(int cap, Deque<Order> orderQueue) {
            this.cap = cap;
            counter = 0;
            queue = orderQueue;
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
        private int counter;

        private final Deque<Order> queue;

        private boolean endWork;

        public DynamicGenerator(int delay, int cap, Deque<Order> orderQueue) {
            this.delay = delay;
            this.cap = cap;
            this.queue = orderQueue;
            this.counter = 0;
            setEndWork(false);
        }

        @Override
        public void run() {
            while (counter < cap) {
                try {
                    synchronized (queue) {
                        queue.push(new Order(counter++));
                    }
                    wait(delay);
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
}
