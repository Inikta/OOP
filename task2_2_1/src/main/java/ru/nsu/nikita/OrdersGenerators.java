package ru.nsu.nikita;

import java.util.concurrent.BlockingQueue;

public class OrdersGenerators {

    public static class StaticGenerator implements OrdersGenerator {

        private final int cap;
        private boolean endWork;
        private int counter;
        private BlockingQueue<Order> queue;

        public StaticGenerator(int cap, BlockingQueue<Order> orderQueue) {
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

    public static class DynamicGenerator implements OrdersGenerator, Runnable {
        private final int delay;
        private final int cap;
        private int counter;

        private BlockingQueue<Order> queue;

        private boolean endWork;

        public DynamicGenerator(int delay, int cap, BlockingQueue<Order> orderQueue) {
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
                    queue.put(new Order(counter++));
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
