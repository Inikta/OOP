package ru.nsu.nikita;

import java.util.ArrayList;

public class ThreadPrimeNumbersDetector extends Thread {
    private final ArrayList<Integer> nums;
    private boolean hasPrime;

    public ThreadPrimeNumbersDetector(ArrayList<Integer> nums) {
        this.nums = nums;
        hasPrime = false;
    }

    @Override
    public void run() {
        super.run();
        for (Integer num : nums) {
            if (PrimeNumberCheck.isPrime(num)) {
                hasPrime = true;
                return;
            }
            hasPrime = false;
        }
    }

    public boolean getResult() {
        return hasPrime;
    }
}
