package ru.nsu.nikita;

import java.io.IOException;

public class App {
    public static void main (String argc[]) throws IOException {
        String file = "shortTextTest.txt";
        String pattern1 = "love";
        String pattern2 = "cactus";
        String pattern3 = "very";


        int a1 = FindSubstring.findInFile(pattern1, file);
        int a2 = FindSubstring.findInFile(pattern2, file);

        //int a3 = FindSubstring.findInString(pattern1, "Joshua loves pies very much");
        //int a4 = FindSubstring.findInString(pattern2, "Joshua loves pies very much");

        //int a5 = FindSubstring.findInString(pattern3, "Joshua loves pies very much");
        int a6 = FindSubstring.findInFile(pattern3, file);


        System.out.printf("love is in position %d\ncactus is in position %d\n", a1, a2);
        //System.out.printf("love is in position %d\ncactus is in position %d\n", a3, a4);
        System.out.printf("very is in position %d\n", a6);

    }
}
