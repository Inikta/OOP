package ru.nsu.nikita.threads;

import ru.nsu.nikita.PrimeNumberCheck;

import java.util.concurrent.Callable;

public class ThreadPrimeNumberCheck implements Callable<Boolean> {

    /**
     * num - integer number, which is going to be checked.
     */
    private final Integer num;
    /**
     * Default constructor.
     *
     * @param num integer number, which is going to be checked.
     */
    public ThreadPrimeNumberCheck(Integer num) {
        this.num = num;
    }

    /**
     * The body of the thread, which executes general prime number checking algorithm.
     */
    @Override
    public Boolean call() {
        if (PrimeNumberCheck.isPrime(num)) {
            /*System.out.println(
                    ": " + hasPrime
                            + " " + num);*/
            return true;
        }

        /*System.out.println(
                ": " + hasPrime
                        + " " + num);*/
        return false;
    }
}
