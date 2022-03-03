package ru.nsu.nikita;

import org.junit.jupiter.api.Test;
import ru.nsu.nikita.threads.MultiThreadListCheck;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class DifferentListsTests {

    public Instant startTime;
    public Instant endTime;

    public int maxThreads = 12;

    public void startTime() {
        startTime = Instant.now();
    }

    public void calculateDuration() {
        endTime = Instant.now();
        long time = Duration.between(startTime, endTime).toMillis();

        System.out.println(time);

    }

    @Test
    public void size1000NotPrimeNumbersListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateAllNotPrimes(0, 1000);

        System.out.println("Sequential:");
        startTime();
        System.out.println(SequentialStreamPrimeNumbersDetector.hasPrime(notPrimesList));

        calculateDuration();

        System.out.println("Parallel:");
        startTime();
        System.out.println(ParallelStreamsPrimeNumbersDetector.hasPrime(notPrimesList));
        calculateDuration();

        System.out.println("Multi-thread:");
        for (int t = 1; t <= maxThreads; t++) {
            System.out.print("\t" + t + "-thread: ");
            MultiThreadListCheck checker = new MultiThreadListCheck(notPrimesList, t);

            startTime();
            checker.hasPrime();
            System.out.print(checker.isHasPrimeNumber() + " - ");
            calculateDuration();
        }
    }

    @Test
    public void size10000NotPrimeNumbersListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateAllNotPrimes(0, 10000);

        System.out.println("Sequential:");
        startTime();
        System.out.println(SequentialStreamPrimeNumbersDetector.hasPrime(notPrimesList));

        calculateDuration();

        System.out.println("Parallel:");
        startTime();
        System.out.println(ParallelStreamsPrimeNumbersDetector.hasPrime(notPrimesList));
        calculateDuration();

        System.out.println("Multi-thread:");
        for (int t = 1; t <= maxThreads; t++) {
            System.out.print("\t" + t + "-thread: ");
            MultiThreadListCheck checker = new MultiThreadListCheck(notPrimesList, t);

            startTime();
            checker.hasPrime();
            System.out.print(checker.isHasPrimeNumber() + " - ");
            calculateDuration();
        }
    }

    @Test
    public void oneSemiPrime1000ListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateSpecified(1048561, 1000);
        notPrimesList.add(1048571);

        System.out.println("Sequential:");
        startTime();
        System.out.println(SequentialStreamPrimeNumbersDetector.hasPrime(notPrimesList));

        calculateDuration();

        System.out.println("Parallel:");
        startTime();
        System.out.println(ParallelStreamsPrimeNumbersDetector.hasPrime(notPrimesList));
        calculateDuration();

        System.out.println("Multi-thread:");
        for (int t = 1; t <= maxThreads; t++) {
            System.out.print("\t" + t + "-thread: ");
            MultiThreadListCheck checker = new MultiThreadListCheck(notPrimesList, t);

            startTime();
            checker.hasPrime();
            System.out.print(checker.isHasPrimeNumber() + " - ");
            calculateDuration();
        }
    }

    @Test
    public void oneSemiPrime10000ListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateSpecified(1048561, 10000);
        notPrimesList.add(1048571);

        System.out.println("Sequential:");
        startTime();
        System.out.println(SequentialStreamPrimeNumbersDetector.hasPrime(notPrimesList));

        calculateDuration();

        System.out.println("Parallel:");
        startTime();
        System.out.println(ParallelStreamsPrimeNumbersDetector.hasPrime(notPrimesList));
        calculateDuration();

        System.out.println("Multi-thread:");
        for (int t = 1; t <= maxThreads; t++) {
            System.out.print("\t" + t + "-thread: ");
            MultiThreadListCheck checker = new MultiThreadListCheck(notPrimesList, t);

            startTime();
            checker.hasPrime();
            System.out.print(checker.isHasPrimeNumber() + " - ");
            calculateDuration();
        }
    }

    @Test
    public void oneSemiPrime100000ListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateSpecified(1048561, 100000);
        notPrimesList.add(1048571);

        System.out.println("Sequential:");
        startTime();
        System.out.println(SequentialStreamPrimeNumbersDetector.hasPrime(notPrimesList));

        calculateDuration();

        System.out.println("Parallel:");
        startTime();
        System.out.println(ParallelStreamsPrimeNumbersDetector.hasPrime(notPrimesList));
        calculateDuration();

        System.out.println("Multi-thread:");
        for (int t = 1; t <= maxThreads; t++) {
            System.out.print("\t" + t + "-thread: ");
            MultiThreadListCheck checker = new MultiThreadListCheck(notPrimesList, t);

            startTime();
            checker.hasPrime();
            System.out.print(checker.isHasPrimeNumber() + " - ");
            calculateDuration();
        }
    }
}
