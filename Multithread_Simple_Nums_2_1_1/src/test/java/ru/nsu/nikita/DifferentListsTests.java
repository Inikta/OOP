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
    public void randomListTimeTest() {
        List<Integer> randomList = new ListGenerators().generateRandom(1000000, 0, 1000000);

        System.out.println("Sequential:");
        startTime();
        SequentialStreamPrimeNumbersDetector.hasPrime(randomList);
        calculateDuration();

        System.out.println("Parallel:");
        startTime();
        ParallelStreamsPrimeNumbersDetector.hasPrime(randomList);
        calculateDuration();

        System.out.println("Multi-thread:");
        for (int t = 1; t <= maxThreads; t++) {
            System.out.print("\t" + t + "-thread: ");
            startTime();
            System.out.print(MultiThreadListCheck.hasPrime(randomList, t) + " - ");
            calculateDuration();
        }
    }

    @Test
    public void longPrimeNumbersListTest() {
        List<Integer> primesList = new ListGenerators().generateAllPrimes(0, 1000000);

        System.out.println("Sequential:");
        startTime();
        System.out.println(SequentialStreamPrimeNumbersDetector.hasPrime(primesList));
        calculateDuration();

        System.out.println("Parallel:");
        startTime();
        System.out.println(ParallelStreamsPrimeNumbersDetector.hasPrime(primesList));
        calculateDuration();

        System.out.println("Multi-thread:");
        for (int t = 1; t <= maxThreads; t++) {
            System.out.print("\t" + t + "-thread: ");
            startTime();
            System.out.print(MultiThreadListCheck.hasPrime(primesList, t) + " - ");
            calculateDuration();
        }
    }

    @Test
    public void size1000NotPrimeNumbersListTest() {
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
            startTime();
            System.out.print(MultiThreadListCheck.hasPrime(notPrimesList, t) + " - ");
            calculateDuration();
        }
    }

    @Test
    public void size10000NotPrimeNumbersListTest() {
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
            startTime();
            System.out.print(MultiThreadListCheck.hasPrime(notPrimesList, t) + " - ");
            calculateDuration();
        }
    }

    @Test
    public void size100000NotPrimeNumbersListTest() {
        List<Integer> notPrimesList = new ListGenerators().generateAllNotPrimes(0, 100000);

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
            startTime();
            System.out.print(MultiThreadListCheck.hasPrime(notPrimesList, t) + " - ");
            calculateDuration();
        }
    }

    @Test
    public void size1000000NotPrimeNumbersListTest() {
        List<Integer> notPrimesList = new ListGenerators().generateAllNotPrimes(0, 1000000);

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
            startTime();
            System.out.print(MultiThreadListCheck.hasPrime(notPrimesList, t) + " - ");
            calculateDuration();
        }
    }

    @Test
    public void size10000000NotPrimeNumbersListTest() {
        List<Integer> notPrimesList = new ListGenerators().generateAllNotPrimes(0, 10000000);

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
            startTime();
            System.out.print(MultiThreadListCheck.hasPrime(notPrimesList, t) + " - ");
            calculateDuration();
        }
    }

   /* @Test
    public void size50000000NotPrimeNumbersListTest() {
        List<Integer> notPrimesList = new ListGenerators().generateAllNotPrimes(0, 50000000);

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
            startTime();
            System.out.print(MultiThreadListCheck.hasPrime(notPrimesList, t) + " - ");
            calculateDuration();
        }
    }*/
}
