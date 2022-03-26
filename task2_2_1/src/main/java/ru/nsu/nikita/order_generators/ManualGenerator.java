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
    public synchronized void run() {
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
                        pizzeria.getOrdersQueue().addLast(new Order(pizzeria.orderCounter.getAndAdd(1)));
                        notifyAll();
                }
                case 11 -> {
                    System.out.print("Orders to add: ");
                    int parameter = reader.nextInt();
                        for (int i = 0; i < parameter; i++) {
                            pizzeria.getOrdersQueue().addLast(new Order(pizzeria.orderCounter.getAndAdd(1)));
                        }
                        notifyAll();
                }
                case 666 -> {
                        for (int i = 0; i < pizzeria.getBakersAmount(); i++) {
                            pizzeria.getOrdersQueue().addFirst(new Order(true, pizzeria.orderCounter.getAndAdd(1)));
                        }
                        notifyAll();
                        for (int i = 0; i < pizzeria.getSuppliersAmount(); i++) {
                            pizzeria.getStorageQueue().addFirst(new Order(true, pizzeria.orderCounter.getAndAdd(1)));
                        notifyAll();
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