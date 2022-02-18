package ru.nsu.nikita;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IntegerArrayListGenerator {
    private final int size;
    private final int leftBorder, rightBorder;

    public IntegerArrayListGenerator(int size, int leftBorder, int rightBorder) {
        this.size = size;
        this.leftBorder = leftBorder;
        this.rightBorder = rightBorder;
    }

    public List<Integer> generate() {
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            newList.add(new Random().nextInt(rightBorder) + leftBorder);
        }

        return newList;
    }
}
