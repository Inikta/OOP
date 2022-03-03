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
    public void size512NotPrimeNumbersListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateAllNotPrimes(0, 512);

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
    public void size1024NotPrimeNumbersListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateAllNotPrimes(0, 1024);

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
    public void size2048NotPrimeNumbersListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateAllNotPrimes(0, 2048);

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
    public void size4096NotPrimeNumbersListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateAllNotPrimes(0, 4096);

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
    public void size16392NotPrimeNumbersListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateAllNotPrimes(0, 16392);

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
    public void size32784NotPrimeNumbersListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateAllNotPrimes(0, 32784);

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
    public void oneSemiPrime512ListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateSpecified(1048561, 512);
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
    public void oneSemiPrime1024ListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateSpecified(1048561, 1024);
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
    public void oneSemiPrime2048ListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateSpecified(1048561, 2048);
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
    public void oneSemiPrime4096ListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateSpecified(1048561, 4096);
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
    public void oneSemiPrime8192ListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateSpecified(1048561, 8192);
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
    public void oneSemiPrime16392ListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateSpecified(1048561, 16392);
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
    public void oneSemiPrime32784ListTest() throws InterruptedException {
        List<Integer> notPrimesList = new ListGenerators().generateSpecified(1048561, 32784);
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
