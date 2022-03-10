package ru.nsu.nikita;

public class Order {

    private final int number;
    private boolean ready;

    private boolean endWork;

    public Order(int orderNum) {
        number = orderNum;
        ready = false;
        endWork = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isEndWork() {
        return endWork;
    }

    public void setEndWork(boolean endWork) {
        this.endWork = endWork;
    }
}
