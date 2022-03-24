package ru.nsu.nikita.order_generators;

public class Order {
    private int number;
    private boolean inWork;
    private boolean ready;
    private boolean inStorage;
    private boolean inBag;
    private boolean delivered;
    private boolean endWork;

    public Order() {
        this.number = 0;
        this.inWork = false;
        this.ready = false;
        this.inStorage = false;
        this.inBag = false;
        this.delivered = false;
        this.endWork = false;
    }

    public Order(int number) {
        this.number = number;
        this.inWork = false;
        this.ready = false;
        this.inStorage = false;
        this.inBag = false;
        this.delivered = false;
        this.endWork = false;
    }

    public Order(boolean endWork, int number) {
        this.number = number;
        this.inWork = false;
        this.ready = true;
        this.inStorage = false;
        this.inBag = false;
        this.delivered = false;

        this.endWork = true;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isInWork() {
        return inWork;
    }

    public void setInWork(boolean inWork) {
        this.inWork = inWork;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public boolean isInStorage() {
        return inStorage;
    }

    public void setInStorage(boolean inStorage) {
        this.inStorage = inStorage;
    }

    public boolean isEndWork() {
        return endWork;
    }

    public boolean isInBag() {
        return inBag;
    }

    public void setInBag(boolean inBag) {
        this.inBag = inBag;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public void setEndWork(boolean endWork) {
        this.endWork = endWork;
    }
}
