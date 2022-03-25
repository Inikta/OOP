package ru.nsu.nikita.order_generators;

import ru.nsu.nikita.Pizzeria;

import java.util.Scanner;

public class ManualGenerator extends Thread {

    private final Pizzeria pizzeria;

    public ManualGenerator(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                        Help:
                        0 - help
                        10 - add one order
                        11 - add X orders
                        666 - end work. All remaining orders will not be completed.
                        """);
        int input = scanner.nextInt();

        while (true) {
            switch (input) {
                case 0 -> System.out.println("""
                        Help:
                        0 - help
                        10 - add one order
                        11 - add X orders
                        666 - end work. All remaining orders will not be completed.
                        """);
                case 10 -> pizzeria.getOrdersQueue().addLast(new Order(pizzeria.orderCounter.getAndAdd(1)));
                case 11 -> {
                    System.out.print("Orders to add: ");
                    int parameter = scanner.nextInt();
                    synchronized (pizzeria.getOrdersQueue()) {
                        for (int i = 0; i < parameter; i++) {
                            pizzeria.getOrdersQueue().addLast(new Order(pizzeria.orderCounter.getAndAdd(1)));
                        }
                        notifyAll();
                    }
                }
                case 666 -> {
                    synchronized (pizzeria.getOrdersQueue()) {
                        for (int i = 0; i < pizzeria.getBakersAmount(); i++) {
                            pizzeria.getOrdersQueue().addFirst(new Order(true, pizzeria.orderCounter.getAndAdd(1)));
                        }
                        notifyAll();
                    }
                    synchronized (pizzeria.getStorageQueue()) {
                        for (int i = 0; i < pizzeria.getSuppliersAmount(); i++) {
                            pizzeria.getStorageQueue().addFirst(new Order(true, pizzeria.orderCounter.getAndAdd(1)));
                        }
                        notifyAll();
                    }
                }
            }

            if (input == 666) {
                break;
            }

            input = scanner.nextInt();
        }
            /*case 20 -> {
                int parameter = scanner.nextInt();
                pizzeria.getBakers().get(0).;
            }
            case 21 -> {

            }
            case 30 -> {

            }
            case 31 -> {

            }*/
    }
}