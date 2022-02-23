import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.nikita.ListGenerators;
import ru.nsu.nikita.ParallelStreamsPrimeNumbersDetector;
import ru.nsu.nikita.SequentialStreamPrimeNumbersDetector;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PrimeNumbersTests {

    public Instant startTime;
    public Instant endTime;

    public void startTime() {
        startTime = Instant.now();
    }

    public void calculateDuration() {
        endTime = Instant.now();
        long time = Duration.between(startTime, endTime).toMillis();

        System.out.println(time);

    }

    @Test
    public void algorithmCorrectnessEmptyTest() {

        ArrayList<Integer> arrayList = new ArrayList<>();
        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(arrayList));
    }

    @Test
    public void algorithmCorrectnessZeroTest() {
        Integer[] array = {0};

        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(array));
    }

    @Test
    public void algorithmCorrectnessPositiveListTest() {

        Integer[] arrayFalse = {4, 6, 8, 9, 12, 1000000000, 621547828};
        Integer[] arrayTrue = {4, 6, 8, 9, 12, 1000000000, 621547828, 1};

        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(arrayFalse));
        Assertions.assertTrue(SequentialStreamPrimeNumbersDetector.hasPrime(arrayTrue));
    }

    @Test
    public void algorithmCorrectnessAnyListTest() {

        Integer[] arrayFalse = {4, 6, -8, 9, 12, -1000000000, 621547828};
        Integer[] arrayTrue = {4, -6, 8, 9, 12, 1000000000, -621547828, -1};

        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(arrayFalse));
        Assertions.assertTrue(SequentialStreamPrimeNumbersDetector.hasPrime(arrayTrue));
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
    }

    @Test
    public void longNotPrimeNumbersListTest() {
        List<Integer> primesList = new ListGenerators().generateAllNotPrimes(0, 1000000);

        System.out.println("Sequential:");
        startTime();
        System.out.println(SequentialStreamPrimeNumbersDetector.hasPrime(primesList));

        calculateDuration();

        System.out.println("Parallel:");
        startTime();
        System.out.println(ParallelStreamsPrimeNumbersDetector.hasPrime(primesList));
        calculateDuration();
    }

/*    @Test
    public void ThreadsLargeArrayTest() {

    }*/
}
