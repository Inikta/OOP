package ru.nsu.nikita;

public class Order {

    private final int number;
    private boolean inBakery;
    private boolean inStorage;
    private boolean delivered;

    private boolean endWork;

    public Order(int orderNum) {
        number = orderNum;
        inBakery = false;
        endWork = false;
        inStorage = false;
        delivered = false;
    }

    public Order(boolean endWork) {
        number = -1;
        inBakery = false;
        inStorage = false;
        delivered = false;

        this.endWork = endWork;
    }

    public Order() {
        number = 0;
        inBakery = false;
        endWork = false;
        inStorage = false;
        delivered = false;
    }

    public int getNumber() {
        return number;
    }

    public boolean isInBakery() {
        return inBakery;
    }

    public void setInBakery(boolean inBakery) {
        this.inBakery = inBakery;
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
                ", ready=" + inBakery +
                ", delivered=" + delivered +
                ", endWork=" + endWork +
                '}';
    }
}
