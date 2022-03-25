package ru.nsu.nikita.order_generators;

import ru.nsu.nikita.Pizzeria;

public class AutoGenerator {

    private final Pizzeria pizzeria;

    public AutoGenerator(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    public void generate(int ordersAmount) {
        synchronized (pizzeria.getOrdersQueue()) {
            for (int i = 0; i < ordersAmount; i++) {
                pizzeria.getOrdersQueue().addLast(new Order(pizzeria.orderCounter.getAndAdd(1)));
            }
        }
    }
}
