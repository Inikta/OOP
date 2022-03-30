package ru.nsu.nikita.employee;

public class BakerAttributes {
    private int bakeTime;
    private int number;

    public BakerAttributes(int number, int bakeTime) {
        this.bakeTime = bakeTime;
        this.number = number;
    }

    public int getBakeTime() {
        return bakeTime;
    }

    public void setBakeTime(int bakeTime) {
        this.bakeTime = bakeTime;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
