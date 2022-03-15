package ru.nsu.nikita;

public class Order {

    private final int number;
    private boolean ready;
    private boolean inStorage;
    private boolean delivered;

    private boolean endWork;

    public Order(int orderNum) {
        number = orderNum;
        ready = false;
        endWork = false;
        inStorage = false;
        delivered = false;
    }

    public Order(boolean endWork) {
        number = -1;
        ready = false;
        inStorage = false;
        delivered = false;

        this.endWork = endWork;
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

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isInStorage() {
        return inStorage;
    }

    public void setInStorage(boolean inStorage) {
        this.inStorage = inStorage;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number=" + number +
                ", ready=" + ready +
                ", delivered=" + delivered +
                ", endWork=" + endWork +
                '}';
    }
}
