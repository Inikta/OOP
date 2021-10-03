package ru.nsu.nikita;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class FindInFileTestPack {

    @Test
    void emptyTest () throws IOException {
        String patterns[] = {"\0", "\n", "cactus"};
        int ref[] = {0, -1, -1};
        int tests[] = new int[ref.length];

        for (int i = 0; i < ref.length; i++) {
            tests[i] = FindSubstring.findInFile(patterns[i], "emptyTest.txt");
        }
        Assertions.assertArrayEquals(ref, tests);
    }

    @Test
    void nullNameFile () throws IOException {
        String patterns[] = {"\0", "\n", "cactus"};
        int ref[] = {-2, -2, -2};
        int tests[] = new int[ref.length];

        for (int i = 0; i < ref.length; i++) {
            tests[i] = FindSubstring.findInFile(patterns[i], null);
        }
        Assertions.assertArrayEquals(ref, tests);
    }

    @Test
    void TextTest () throws IOException {
        String patterns[] = {"\0", "\n", "cactus", "love", "very"};
        int ref[] = {28, -1, -1, 7, 18};            //test with word "very" checks for case,
        int tests[] = new int[ref.length];              //when word is partly in one chunk and partly in the next

        for (int i = 0; i < ref.length; i++) {
            tests[i] = FindSubstring.findInFile(patterns[i], "shortTextTest.txt");
        }
        Assertions.assertArrayEquals(ref, tests);
    }
}
