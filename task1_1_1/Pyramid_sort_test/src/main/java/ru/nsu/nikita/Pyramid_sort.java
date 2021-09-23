package ru.nsu.nikita;

public class Pyramid_sort {
    public static void sort(int arr[]) {        //Main method - calls maxHeap method and sorts array in loop
        int count = arr.length;
        maxHeap(arr, count);
        count--;
        while (count > 0) {
            swap(arr, count, 0);        //swap highest and lowest elements of array (first and last)
            maxHeap(arr, count);           //repair heap order
            count--;                        //delete the last (used) element by decreasing tree (heap) size by one
        }

    }

    private static void maxHeap(int arr[], int count) {     //Creates heap by repeatedly calling siftUp method,
        int end = 0;                                        //with shifting current end of tree
        while (end < count) {
            siftUp(arr, 0, end);
            end++;
        }
    }

    private static void siftUp(int arr[], int start, int end) {     //moves bigger elements to the top of the heap
        int child = end;                        //current lowest leaf of the tree
        while (child > start) {
            int parent = (child - 1) / 2;           //formula of parent ID
            if (arr[parent] < arr[child]) {         //swaps elements until cur child is too low
                swap(arr, parent, child);
                child = parent;
            } else
                return;
        }
    }

    private static void swap(int arr[], int a, int b) {     //swap of elements in array arr[]
        int c = arr[a];
        arr[a] = arr[b];
        arr[b] = c;
    }
}
