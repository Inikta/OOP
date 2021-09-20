package ru.nsu.nikita;

public class Pyramid_sort {
    public static void sort(int arr[]) {
        int count = 0;
        for (int i:arr)
            count++;
        maxHeap(arr, count);
        count--;
        while (count > 0) {
            swap(arr, count, 0);
            maxHeap(arr, count);
            count--;
        }

    }

    private static void maxHeap(int arr[], int count) {
        int end = 0;
        while (end < count) {
            siftUp(arr, 0, end);
            end++;
        }
    }

    private static void siftUp(int arr[], int start, int end) {
        int child = end;
        while (child > start) {
            int parent = (child - 1) / 2;
            if (arr[parent] < arr[child]) {
                swap(arr, parent, child);
                child = parent;
            }
            else
                return;
        }
    }

    /*private static void siftDown(int arr[]) {
        if ()
    }*/

    private static void swap(int arr[], int a, int b) {
        int c = arr[a];
        arr[a] = arr[b];
        arr[b] = c;
    }
}
