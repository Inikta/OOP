package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.nikita.threads.MultiThreadListCheck;

import java.util.ArrayList;
import java.util.Arrays;

public class CorrectnessTests {

    /**
     * Tests for checking correctness of used algorithm variations.
     */

    @Test
    public void algorithmCorrectnessEmptyTest() throws InterruptedException {

        ArrayList<Integer> list = new ArrayList<>();
        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(list));
        Assertions.assertFalse(ParallelStreamsPrimeNumbersDetector.hasPrime(list));

        MultiThreadListCheck checker = new MultiThreadListCheck(list, 1);
        checker.hasPrime();
        Assertions.assertFalse(checker.isHasPrimeNumber());

    }

    @Test
    public void algorithmCorrectnessZeroTest() throws InterruptedException {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(list));
        Assertions.assertFalse(ParallelStreamsPrimeNumbersDetector.hasPrime(list));

        MultiThreadListCheck checker = new MultiThreadListCheck(list, 1);
        checker.hasPrime();
        Assertions.assertFalse(checker.isHasPrimeNumber());
    }

    @Test
    public void algorithmCorrectnessPositiveListTest() throws InterruptedException {
        Integer[] arrayFalse = {4, 6, 8, 9, 12, 1000000000, 621547828};
        Integer[] arrayTrue = {4, 6, 8, 9, 12, 1000000000, 621547828, 1};

        ArrayList<Integer> listFalse = new ArrayList<>(Arrays.stream(arrayFalse).toList());
        ArrayList<Integer> listTrue= new ArrayList<>(Arrays.stream(arrayTrue).toList());


        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(listFalse));
        Assertions.assertTrue(SequentialStreamPrimeNumbersDetector.hasPrime(listTrue));

        Assertions.assertFalse(ParallelStreamsPrimeNumbersDetector.hasPrime(listFalse));
        Assertions.assertTrue(ParallelStreamsPrimeNumbersDetector.hasPrime(listTrue));

        MultiThreadListCheck checker1 = new MultiThreadListCheck(listFalse, 1);
        checker1.hasPrime();
        Assertions.assertFalse(checker1.isHasPrimeNumber());

        MultiThreadListCheck checker2= new MultiThreadListCheck(listTrue, 1);
        checker2.hasPrime();
        Assertions.assertTrue(checker2.isHasPrimeNumber());
    }

    @Test
    public void algorithmCorrectnessAnyListTest() throws InterruptedException {

        Integer[] arrayFalse = {4, 6, -8, 9, 12, 1000000000, -621547828};
        Integer[] arrayTrue = {4, 6, 8, 9, -12, 1000000000, -621547828, 1};

        ArrayList<Integer> listFalse = new ArrayList<>(Arrays.stream(arrayFalse).toList());
        ArrayList<Integer> listTrue= new ArrayList<>(Arrays.stream(arrayTrue).toList());

        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(listFalse));
        Assertions.assertTrue(SequentialStreamPrimeNumbersDetector.hasPrime(listTrue));

        Assertions.assertFalse(ParallelStreamsPrimeNumbersDetector.hasPrime(listFalse));
        Assertions.assertTrue(ParallelStreamsPrimeNumbersDetector.hasPrime(listTrue));

        MultiThreadListCheck checker1 = new MultiThreadListCheck(listFalse, 1);
        checker1.hasPrime();
        Assertions.assertFalse(checker1.isHasPrimeNumber());

        MultiThreadListCheck checker2= new MultiThreadListCheck(listTrue, 1);
        checker2.hasPrime();
        Assertions.assertTrue(checker2.isHasPrimeNumber());
    }
}
