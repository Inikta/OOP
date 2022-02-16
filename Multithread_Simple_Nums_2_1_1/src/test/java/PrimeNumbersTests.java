import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.nsu.nikita.SequentialStreamPrimeNumbersDetector;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

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

        Integer[] arrayFalse = {4, 6, 8, 9, 12};
        Integer[] arrayTrue = {4, 6, 8, 9, 12, 1};

        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(arrayFalse));
        Assertions.assertTrue(SequentialStreamPrimeNumbersDetector.hasPrime(arrayTrue));
    }

    @Test
    public void oneSequentialStreamArrayTest() {

        Integer[] arrayFalse = {-4, 6, -8, 9, 12};
        Integer[] arrayTrue = {4, -6, 8, -9, 12, -1};

        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(arrayFalse));
        Assertions.assertTrue(SequentialStreamPrimeNumbersDetector.hasPrime(arrayTrue));
    }
}
