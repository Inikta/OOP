import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.nikita.IntegerArrayListGenerator;
import ru.nsu.nikita.ParallelStreamsPrimeNumbersDetector;
import ru.nsu.nikita.SequentialStreamPrimeNumbersDetector;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PrimeNumbersTests {

    public Instant startTime;
    public Instant endTime;

    @BeforeEach
    public void startTime() {
        startTime = Instant.now();
    }

    @AfterEach
    public void calculateDuration() {
        endTime = Instant.now();
        long time = Duration.between(startTime, endTime).toMillis();

        System.out.println(time);
    }

    @Test
    public void oneSequentialStreamEmptyArrayTest() {

        ArrayList<Integer> arrayList = new ArrayList<>();
        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(arrayList));
    }

    @Test
    public void oneSequentialStreamZeroTest() {
        Integer[] array = {0};

        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(array));
    }

    @Test
    public void oneSequentialStreamPositiveArrayTest() {

        List<Integer> arrayFalse = new IntegerArrayListGenerator(100, 0, 50).generate();
        arrayFalse.add(4);
        List<Integer> arrayTrue = new IntegerArrayListGenerator(100, 0, 50).generate();
        arrayFalse.add(5);

        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(arrayFalse));
        Assertions.assertTrue(SequentialStreamPrimeNumbersDetector.hasPrime(arrayTrue));
    }

    @Test
    public void oneSequentialStreamArrayTest() {

        List<Integer> arrayFalse = new IntegerArrayListGenerator(100, -50, 50).generate();
        arrayFalse.add(-4);
        List<Integer> arrayTrue = new IntegerArrayListGenerator(100, -50, 50).generate();
        arrayFalse.add(-5);

        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(arrayFalse));
        Assertions.assertTrue(SequentialStreamPrimeNumbersDetector.hasPrime(arrayTrue));
    }

    @Test
    public void oneSequentialStreamLargeArrayTest() {
        List<Integer> arrayFalse = new IntegerArrayListGenerator(100000, -100000, 100000).generate();
        arrayFalse.add(4);
        List<Integer> arrayTrue = new IntegerArrayListGenerator(10000, -100000, 100000).generate();
        arrayFalse.add(5);

        System.out.println("Sequential:");
        SequentialStreamPrimeNumbersDetector.hasPrime(arrayFalse);
        //Assertions.assertTrue(SequentialStreamPrimeNumbersDetector.hasPrime(arrayTrue));
    }

    @Test
    public void parallelStreamsLargeArrayTest() {
        List<Integer> arrayFalse = new IntegerArrayListGenerator(100000, -100000, 100000).generate();
        arrayFalse.add(4);
        List<Integer> arrayTrue = new IntegerArrayListGenerator(10000, -100000, 100000).generate();
        arrayFalse.add(5);

        System.out.println("Parallel:");
        ParallelStreamsPrimeNumbersDetector.hasPrime(arrayFalse);
        //Assertions.assertTrue(ParallelStreamsPrimeNumbersDetector.hasPrime(arrayTrue));
    }

/*    @Test
    public void ThreadsLargeArrayTest() {

    }*/
}
