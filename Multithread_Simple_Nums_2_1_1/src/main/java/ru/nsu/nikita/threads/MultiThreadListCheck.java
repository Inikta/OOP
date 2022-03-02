package ru.nsu.nikita.threads;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadListCheck {

    /**
     * Multiple threads creation and managing method. Makes multi-thread checking of list having prime numbers.
     *
     * @param list       list of integers to check.
     * @param maxThreads threads amount for computations.
     * @return true, if list has prime numbers. False, otherwise.
     */
    public static boolean hasPrime(List<Integer> list, int maxThreads) {
        int counter = 0;

        int threadsAmount = getAvailableThreads(maxThreads);
        for (int i = 0; i < list.size(); i += threadsAmount) {
            List<ThreadPrimeNumberCheck> threads = new ArrayList<>();
            threadsAmount = Math.min(list.size() - i, threadsAmount);

            for (int j = 0; j < threadsAmount; j++) {
                threads.add(new ThreadPrimeNumberCheck(list.get(counter++)));
            }

            for (ThreadPrimeNumberCheck thread : threads) {
                thread.run();
                if (thread.hasPrime) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Compares requested amount of threads with number of available ones.
     *
     * @param maxThreads requested amount of threads.
     * @return requested amount, if this amount is available. Or available amount, if it is less than requested.
     */

    private static int getAvailableThreads(int maxThreads) {
        int currentAvailable = Runtime.getRuntime().availableProcessors();
        if (maxThreads < currentAvailable) {
            return maxThreads;
        } else {
            System.out.println("There are only "
                    + currentAvailable
                    + " threads available to use, instead of requested "
                    + maxThreads + ".");
            return currentAvailable;
        }
    }
}
