package ru.nsu.nikita;

import java.util.Arrays;
import java.util.List;

public class SequentialStreamPrimeNumbersDetector {

    /**
     * One-thread prime numbers detector for integers.
     * Makes list from array and executes method for lists.
     *
     * @param array - integer numbers array.
     * @return - true, if prime number was found. Otherwise, false.
     */

    public static Boolean hasPrime(Integer[] array) {
        List<Integer> nums;
        try {
            nums = Arrays.asList(array);
        } catch (NullPointerException nullExc) {
            nullExc.printStackTrace();
            throw new NullPointerException();
        }
        return hasPrime(nums);
    }

    /**
     * One-thread prime numbers detector for integers.
     * Founds if list contains integer prime number.
     *
     * @param nums - integer numbers list.
     * @return - true, if prime number was found. Otherwise, false.
     */

    public static Boolean hasPrime(List<Integer> nums) {
        boolean prime;
        try {
            prime = nums.stream()
                    .anyMatch(PrimeNumberCheck::isPrime);
        } catch (NullPointerException nullExc) {
            nullExc.printStackTrace();
            throw new NullPointerException();
        }
        return prime;
    }
}
