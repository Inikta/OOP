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
        int hasPrime = 0;
        int threadsAmount = getAvailableThreads(maxThreads);
        for (int i = 0; i < list.size(); i += threadsAmount) {
            List<ThreadPrimeNumberCheck> threads = new ArrayList<>();
            threadsAmount = Math.min(list.size() - i, threadsAmount);

            for (int j = 0; j < threadsAmount; j++) {
                threads.add(new ThreadPrimeNumberCheck(list.get(counter++)));
                threads.get(j).setPriority(10 - j);
            }

            for (ThreadPrimeNumberCheck thread : threads) {
                thread.start();
            }

            for (int j = 0; j < threads.size(); j++) {
                if (threads.get(j).getResult()) {
                    hasPrime++;
                }
            }

            if (hasPrime > 0) {
                break;
            }
        }

        if (hasPrime > 0) {
            return true;
        } else {
            return false;
        }
    }

    private static int getAvailableThreads(int maxThreads) {
        int currentAvailable = Runtime.getRuntime().availableProcessors();
        if (maxThreads < currentAvailable) {
            return maxThreads;
        } else {
            System.out.println("There are only "
                    + currentAvailable
                    + " threads available to use, instead of requested"
                    + maxThreads + ".");
            return currentAvailable;
        }
    }
}
