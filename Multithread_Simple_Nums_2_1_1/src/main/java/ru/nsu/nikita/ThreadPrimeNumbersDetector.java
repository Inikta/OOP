package ru.nsu.nikita;

import java.util.ArrayList;

public class ThreadPrimeNumbersDetector extends Thread {
    private final Integer num;
    private boolean hasPrime;

    public ThreadPrimeNumbersDetector(Integer num) {
        this.num = num;
        hasPrime = false;
    }

    @Override
    public void run() {
        super.run();
        hasPrime = PrimeNumberCheck.isPrime(num);
    }

    public boolean getResult() {
        return hasPrime;
    }
}
