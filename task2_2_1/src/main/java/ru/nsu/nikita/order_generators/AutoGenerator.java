package ru.nsu.nikita.order_generators;

import ru.nsu.nikita.Pizzeria;

public class AutoGenerator {

    private final Pizzeria pizzeria;

    public AutoGenerator(Pizzeria pizzeria) {
        this.pizzeria = pizzeria;
    }

    public void generate(int ordersAmount) {
        for (int i = 0; i < ordersAmount; i++) {
            pizzeria.getOrdersQueue().addLast(new Order(pizzeria.orderCounter.getAndAdd(1)));
        }
        for (int i = 0; i < pizzeria.getAttributes().getBakersAmount(); i++) {
            pizzeria.getOrdersQueue().addLast(new Order(true, pizzeria.orderCounter.getAndAdd(-100)));
        }
    }
}
