package ru.nsu.nikita.threads;

import ru.nsu.nikita.PrimeNumberCheck;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadListCheck {

    volatile private boolean hasPrimeNumber = false;
    private final List<Integer> list;
    private final int maxThreads;

    /**
     * Multiple threads creation and managing method. Makes multi-thread checking of list having prime numbers.
     *
     * @param list       list of integers to check.
     * @param maxThreads threads amount for computations.
     */

    public MultiThreadListCheck(List<Integer> list, int maxThreads) {
        this.list = list;
        this.maxThreads = maxThreads;
    }

    public void hasPrime() throws InterruptedException {
        int counter = 0;
        int threadsAmount = getAvailableThreads(maxThreads);

        for (int i = 0; i < list.size(); i += threadsAmount) {
            threadsAmount = Math.min(threadsAmount, list.size() - i);

            List<ThreadPrimeNumberCheck> threads = new ArrayList<>();

            for (int j = 0; j < threadsAmount; j++) {
                threads.add(new ThreadPrimeNumberCheck(list.get(counter++)));       //create thread for next number
                threads.get(j).start();                                             //start thread
                threads.get(j).join();
            }

            for (ThreadPrimeNumberCheck t : threads) {                           //wait for tasks complete
                t.join();
            }

            if (hasPrimeNumber) {
                return;
            }
        }

    }

    /**
     * Compares requested amount of threads with number of available ones.
     *
     * @param maxThreads requested amount of threads.
     * @return requested amount, if this amount is available. Or available amount, if it is less than requested.
     */

    private static int getAvailableThreads(int maxThreads) {
        int currentAvailable = Runtime.getRuntime().availableProcessors();
        if (maxThreads <= currentAvailable) {
            return maxThreads;
        } else {
            System.out.println("There are only "
                    + currentAvailable
                    + " threads available to use, instead of requested "
                    + maxThreads + ".");
            return currentAvailable;
        }
    }

    /**
     * Get result of the list analysis.
     * @return True or False.
     */

    public boolean isHasPrimeNumber() {
        return hasPrimeNumber;
    }

    public class ThreadPrimeNumberCheck extends Thread {

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
        public void run() {
            hasPrimeNumber = PrimeNumberCheck.isPrime(num);
            // System.out.println(this.getName() + ": " + num + " - " + hasPrimeNumber);    - for debugging purposes
        }
    }
}
