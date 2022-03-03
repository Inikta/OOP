package ru.nsu.nikita;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListGenerators {

    /**
     * Generates list filled with random integer numbers.
     * @param size size of the list.
     * @param leftBorder left border of area of generated numbers.
     * @param rightBorder right border of area of generated numbers.
     * @return generated list.
     */

    public List<Integer> generateRandom(int size, int leftBorder, int rightBorder) {
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            newList.add(new Random().nextInt(rightBorder) + leftBorder);
        }

        return newList;
    }

    /**
     * Generate list of prime numbers. Size is regulated by amount of prime numbers in area.
     * @param leftBorder left border of area of generated numbers.
     * @param rightBorder right border of area of generated numbers.
     * @return list filled with prime integer numbers.
     */

    public List<Integer> generateAllPrimes(int leftBorder, int rightBorder) {
        List<Integer> list = new ArrayList<>();
        for (int i = leftBorder; i <= rightBorder; i++) {
            if (PrimeNumberCheck.isPrime(i)) {
                list.add(i);
            }
        }

        return list;
    }

    /**
     * Generate list of not prime numbers. Size is regulated by amount of not prime numbers in area.
     * @param leftBorder left border of area of generated numbers.
     * @param rightBorder right border of area of generated numbers.
     * @return list filled with not prime integer numbers.
     */

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
