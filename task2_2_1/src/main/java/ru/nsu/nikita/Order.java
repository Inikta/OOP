package ru.nsu.nikita;

public class Order {

    private final int number;
    private boolean ready;
    private boolean delivered;

    private boolean endWork;

    public Order(int orderNum) {
        number = orderNum;
        ready = false;
        endWork = false;
        delivered = false;
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
