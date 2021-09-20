package ru.nsu.nikita;

public class App {
    public static void main (String argc[]) {
        int test[] = {1};
        Pyramid_sort.sort(test);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
    }
}
