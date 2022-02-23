package ru.nsu.nikita;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListGenerators {
    public List<Integer> generateRandom(int size, int leftBorder, int rightBorder) {
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            newList.add(new Random().nextInt(rightBorder) + leftBorder);
        }

        return newList;
    }

    public List<Integer> generateAllPrimes(int leftBorder, int rightBorder) {
        List<Integer> list = new ArrayList<>();
        for (int i = leftBorder; i <= rightBorder; i++) {
            if (PrimeNumberCheck.isPrime(i)) {
                list.add(i);
            }
        }

        return list;
    }

    public List<Integer> generateAllNotPrimes(int leftBorder, int rightBorder) {
        List<Integer> list = new ArrayList<>();
        for (int i = leftBorder; i <= rightBorder; i++) {
            if (!PrimeNumberCheck.isPrime(i)) {
                list.add(i);
            }
        }

        return list;
    }
}
