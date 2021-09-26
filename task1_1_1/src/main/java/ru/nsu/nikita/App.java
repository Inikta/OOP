package ru.nsu.nikita;

public class App {
    public static void main (String argc[]) {       //manual test
        int test[] = {1};
        PyramidSort.sort(test);
        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
    }
}
