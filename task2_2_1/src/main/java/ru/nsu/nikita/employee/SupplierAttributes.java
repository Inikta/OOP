package ru.nsu.nikita.employee;

public class SupplierAttributes {
    private int number;
    private int bagLimit;
    private int deliveryTime;

    public SupplierAttributes(int number, int bagLimit, int deliveryTime) {
        this.number = number;
        this.bagLimit = bagLimit;
        this.deliveryTime = deliveryTime;
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
}
