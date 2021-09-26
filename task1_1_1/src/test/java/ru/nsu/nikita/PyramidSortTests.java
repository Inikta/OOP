package ru.nsu.nikita;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PyramidSortTests {
    private final PyramidSort pyramidSort = new PyramidSort();

    @Test
    void emptyArray() {
        int arr[] = {};
        int ref[] = {};

        pyramidSort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void taskArray() {
        int arr[] = {5, 4, 3, 2, 1};
        int ref[] = {1, 2, 3, 4, 5};

        pyramidSort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void equalsArray() {
        int arr[] = {0, 0, 0, 0, 0};
        int ref[] = {0, 0, 0, 0, 0};

        pyramidSort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void oneElementArray() {
        int arr[] = {1};
        int ref[] = {1};

        pyramidSort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void twoElementsArray() {
        int arr[] = {2, 1};
        int ref[] = {1, 2};

        pyramidSort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void sortedArray() {
        int arr[] = {1, 2, 3, 4, 5};
        int ref[] = {1, 2, 3, 4, 5};

        pyramidSort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void someArray() {
        int arr[] = {101, -6, -154, 77, 0, 1, 1, 101, 8, 9, -6};
        int ref[] = {-154, -6, -6, 0, 1, 1, 8, 9, 77, 101, 101};

        pyramidSort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void Array1000() {
        int arr[] = new int[1000];
        int ref[] = new int[1000];

        for (int i = 0; i < 1000; i++)
        {
            arr[i] = 1000 - i;
            ref[i] = 1 + i;
        }

        pyramidSort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void Array10000() {
        int arr[] = new int[10000];
        int ref[] = new int[10000];

        for (int i = 0; i < 10000; i++)
        {
            arr[i] = 10000 - i;
            ref[i] = 1 + i;
        }

        pyramidSort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }
}
