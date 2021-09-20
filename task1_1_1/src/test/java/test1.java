import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.nikita.Pyramid_sort;

public class test1 {
    private final Pyramid_sort psort = new Pyramid_sort();

    @Test
    void emptyArray() {
        int arr[] = {};
        int ref[] = {};

        psort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void taskArray() {
        int arr[] = {5, 4, 3, 2, 1};
        int ref[] = {1, 2, 3, 4, 5};

        psort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void equalsArray() {
        int arr[] = {0, 0, 0, 0, 0};
        int ref[] = {0, 0, 0, 0, 0};

        psort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void oneArray() {
        int arr[] = {1};
        int ref[] = {1};

        psort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void twoArray() {
        int arr[] = {2, 1};
        int ref[] = {1, 2};

        psort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void sortedArray() {
        int arr[] = {1, 2, 3, 4, 5};
        int ref[] = {1, 2, 3, 4, 5};

        psort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }

    @Test
    void someArray() {
        int arr[] = {101, -6, -154, 77, 0, 1, 1, 101, 8, 9, -6};
        int ref[] = {-154, -6, -6, 0, 1, 1, 8, 9, 77, 101, 101};

        psort.sort(arr);
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

        psort.sort(arr);
        Assertions.assertArrayEquals(ref, arr);
    }
}
