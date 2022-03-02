package ru.nsu.nikita.threads;

import ru.nsu.nikita.PrimeNumberCheck;

public class ThreadPrimeNumberCheck implements Runnable {

    /**
     * num - integer number, which is going to be checked.
     */
    private final Integer num;
    volatile boolean hasPrime;
    volatile boolean finish;

    /**
     * Default constructor.
     *
     * @param num integer number, which is going to be checked.
     */
    public ThreadPrimeNumberCheck(Integer num) {
        this.num = num;
        hasPrime = false;
        finish = false;
    }

    /**
     * The body of the thread, which executes general prime number checking algorithm.
     */
    @Override
    public void run() {
        if (!finish) {
            hasPrime = (PrimeNumberCheck.isPrime(num));

            if (hasPrime) {
                finish = true;
            }
        }
    }
}
