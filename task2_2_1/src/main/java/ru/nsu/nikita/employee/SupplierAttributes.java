package ru.nsu.nikita.employee;

public class SupplierAttributes {
    private int number;
    private int bagLimit;
    private int deliveryTime;
    private int waitingTime;

    /**
     * Data structure for Supplier parameters.
     * @param number id of the supplier.
     * @param bagLimit maximum amount of pizzas supplier can rake
     * @param deliveryTime time spending on delivering of each pizza.
     * @param waitingTime time, which supplier will wait for new orders in storage if its empty, but there is/are pizza('s) in the bag.
     */
    public SupplierAttributes(int number, int bagLimit, int deliveryTime, int waitingTime) {
        this.number = number;
        this.bagLimit = bagLimit;
        this.deliveryTime = deliveryTime;
        this.waitingTime = waitingTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getBagLimit() {
        return bagLimit;
    }

    public void setBagLimit(int bagLimit) {
        this.bagLimit = bagLimit;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Override
    public String toString() {
        return "SupplierAttributes{" +
                "number=" + number +
                ", bagLimit=" + bagLimit +
                ", deliveryTime=" + deliveryTime +
                ", waitingTime=" + waitingTime +
                '}';
    }
}
