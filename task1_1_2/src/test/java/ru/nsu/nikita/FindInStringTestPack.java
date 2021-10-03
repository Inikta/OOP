package ru.nsu.nikita;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class FindInStringTestPack {

    @Test
    void emptyTest () throws IOException {
        String patterns[] = {"\0", "\n", "cactus"};
        int ref[] = {0, -1, -1};
        int tests[] = new int[ref.length];
        String sample = "\0";
        for (int i = 0; i < ref.length; i++) {
            tests[i] = FindSubstring.findInString(patterns[i], sample);
        }
        Assertions.assertArrayEquals(ref, tests);
    }

    @Test
    void justString () throws IOException {
        String patterns[] = {"\0", "\n", "cactus", "pineapple"};
        int ref[] = {47, 27, 38, -1};
        int tests[] = new int[ref.length];
        String sample = "Joshua loves pies very much\nand hates cactuses.\0";
        for (int i = 0; i < ref.length; i++) {
            tests[i] = FindSubstring.findInString(patterns[i], sample);
        }
        Assertions.assertArrayEquals(ref, tests);
    }
}
