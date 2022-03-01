package ru.nsu.nikita.threads;

import ru.nsu.nikita.PrimeNumberCheck;

public class ThreadPrimeNumberCheck extends Thread {

    /**
     * num - integer number, which is going to be checked.
     */
    private final Integer num;
    /**
     * hasPrime - boolean value storing the result of checking. False by default.
     */
    private boolean hasPrime;

    /**
     * Default constructor.
     *
     * @param num integer number, which is going to be checked.
     */
    public ThreadPrimeNumberCheck(Integer num) {
        this.num = num;
        hasPrime = false;
    }

    /**
     * The body of the thread, which executes general prime number checking algorithm.
     */
    @Override
    public void run() {
        super.run();
        hasPrime = PrimeNumberCheck.isPrime(num);
        System.out.println(
                this.getName()
                        + ": " + hasPrime
                        + " " + num);
    }

    /**
     * Returns result of the thread computations.
     *
     * @return true, if number is prime. False, otherwise.
     */

    public boolean getResult() {
        return hasPrime;
    }
}
