package ru.nsu.nikita.order_generators;

import ru.nsu.nikita.Pizzeria;

import java.util.Scanner;

public class ManualGenerator extends Thread {

    private final Pizzeria pizzeria;
    private final Scanner reader;

    public ManualGenerator(Pizzeria pizzeria, Scanner reader) {
        this.pizzeria = pizzeria;
        this.reader = reader;
    }

    @Override
    public void run() {
        System.out.println("""
                Help:
                0 - help
                10 - add one order
                11 - add X orders
                666 - end work. All remaining orders will not be completed.
                """);
        int input = reader.nextInt();

        while (true) {
            switch (input) {
                case 0 -> System.out.println("""
                        Help:
                        0 - help
                        10 - add one order
                        11 - add X orders
                        666 - end work. All remaining orders will not be completed.
                        """);
                case 10 -> {
                    synchronized (pizzeria.getOrdersQueue()) {
                        pizzeria.getOrdersQueue().addLast(new Order(pizzeria.orderCounter.getAndAdd(1)));
                        pizzeria.getOrdersQueue().notifyAll();
                    }
                }
                case 11 -> {
                    System.out.print("Orders to add: ");
                    int parameter = reader.nextInt();
                    synchronized (pizzeria.getOrdersQueue()) {
                        for (int i = 0; i < parameter; i++) {
                            pizzeria.getOrdersQueue().addLast(new Order(pizzeria.orderCounter.getAndAdd(1)));
                        }
                        pizzeria.getOrdersQueue().notifyAll();
                    }
                }
                case 666 -> {
                    synchronized (pizzeria.getOrdersQueue()) {

                        for (int i = 0; i < pizzeria.getBakersAmount(); i++) {
                            pizzeria.getOrdersQueue().addFirst(new Order(true, pizzeria.orderCounter.getAndAdd(1)));
                        }
                        pizzeria.getOrdersQueue().notifyAll();
                    }
                    synchronized (pizzeria.getStorageQueue()) {

                        for (int i = 0; i < pizzeria.getSuppliersAmount(); i++) {
                            pizzeria.getStorageQueue().addFirst(new Order(true, pizzeria.orderCounter.getAndAdd(1)));
                        }
                        pizzeria.getStorageQueue().notifyAll();
                    }
                    reader.close();
                }
            }

            if (input == 666) {
                break;
            }

            input = reader.nextInt();
        }
    }
}