package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.nikita.threads.MultiThreadListCheck;

import java.util.ArrayList;
import java.util.Arrays;

public class CorrectnessTests {
    @Test
    public void algorithmCorrectnessEmptyTest() {

        ArrayList<Integer> list = new ArrayList<>();
        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(list));
        Assertions.assertFalse(ParallelStreamsPrimeNumbersDetector.hasPrime(list));
        Assertions.assertFalse(MultiThreadListCheck.hasPrime(list, 2));

    }

    @Test
    public void algorithmCorrectnessZeroTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);

        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(list));
        Assertions.assertFalse(ParallelStreamsPrimeNumbersDetector.hasPrime(list));
        Assertions.assertFalse(MultiThreadListCheck.hasPrime(list, 2));
    }

    @Test
    public void algorithmCorrectnessPositiveListTest() {
        Integer[] arrayFalse = {4, 6, 8, 9, 12, 1000000000, 621547828};
        Integer[] arrayTrue = {4, 6, 8, 9, 12, 1000000000, 621547828, 1};

        ArrayList<Integer> listFalse = new ArrayList<>(Arrays.stream(arrayFalse).toList());
        ArrayList<Integer> listTrue= new ArrayList<>(Arrays.stream(arrayTrue).toList());


        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(listFalse));
        Assertions.assertTrue(SequentialStreamPrimeNumbersDetector.hasPrime(listTrue));

        Assertions.assertFalse(ParallelStreamsPrimeNumbersDetector.hasPrime(listFalse));
        Assertions.assertTrue(ParallelStreamsPrimeNumbersDetector.hasPrime(listTrue));

       // Assertions.assertFalse(MultiThreadListCheck.hasPrime(listFalse, 2));
        Assertions.assertTrue(MultiThreadListCheck.hasPrime(listTrue, 1));
    }

    @Test
    public void algorithmCorrectnessAnyListTest() {

        Integer[] arrayFalse = {4, 6, -8, 9, 12, 1000000000, -621547828};
        Integer[] arrayTrue = {4, 6, 8, 9, -12, 1000000000, -621547828, 1};

        ArrayList<Integer> listFalse = new ArrayList<>(Arrays.stream(arrayFalse).toList());
        ArrayList<Integer> listTrue= new ArrayList<>(Arrays.stream(arrayTrue).toList());

        Assertions.assertFalse(SequentialStreamPrimeNumbersDetector.hasPrime(listFalse));
        Assertions.assertTrue(SequentialStreamPrimeNumbersDetector.hasPrime(listTrue));

        Assertions.assertFalse(ParallelStreamsPrimeNumbersDetector.hasPrime(listFalse));
        Assertions.assertTrue(ParallelStreamsPrimeNumbersDetector.hasPrime(listTrue));

        boolean a1 = MultiThreadListCheck.hasPrime(listFalse, 2);
        boolean a2 = MultiThreadListCheck.hasPrime(listTrue, 2);
        Assertions.assertFalse(a1);
        Assertions.assertTrue(a2);
    }
}
