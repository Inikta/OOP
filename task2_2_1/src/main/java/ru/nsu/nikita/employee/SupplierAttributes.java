package ru.nsu.nikita.employee;

public class SupplierAttributes {
    private int number;
    private int bagLimit;
    private int deliveryTime;
    private int waitingTime;

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
}
