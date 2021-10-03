package ru.nsu.nikita;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FindSubstring {

    public static int findInFile(String subStr, String inFileName) throws IOException {
        int patternLen = subStr.length();
        char[] pattern = subStr.toCharArray();

        BufferedReader inFile = null;

        if (inFileName != null) {
            inFile = new BufferedReader(new FileReader(inFileName));
        }

        int chunkSize = patternLen * 6;                             //size of current loaded part of text
        char[] chunk = new char[chunkSize];                         //consists of: pattern + '$' + part of text
        System.arraycopy(pattern, 0, chunk, 0, patternLen);    //fill chunk with pattern before the delimiter and the text part
        chunk[patternLen] = '$';                                //delimiter
        int startPos = patternLen + 1;                              //start index of text

        char[] blankArea = new char[chunkSize - startPos];          //array for clearing text part of chunk

        int maxLen = 0;                                 //last pattern part occurrence length in current chunk
        int end = 0;                                    //end of text flag (end of loop condition)
        int textPos = 0;                                //current position in whole text => returned index if pattern was found
        int residualLen = 0;

        while (end != -1) {                         //compute cycle until end of file is found

            if (inFile != null) {                           //TO-DO: deplete algorithm in two branches for file and string on input
                end = inFile.read(chunk, startPos + residualLen, chunk.length - startPos - maxLen);
            }
            else {
                return -2;
            }

            char[] residualBuffer = new char[patternLen - 1];       //buffer for part of pattern that was left in previous chunk
            int[] findInStringRes = findSubstring(pattern, chunk, chunkSize, startPos);  //returns maxLen, maxLenInd and textPos for current chunk

            if (findInStringRes[0] == patternLen) {     //if pattern was found, then return global position in text
                textPos += findInStringRes[2];
                return textPos - startPos;
            }
            maxLen = findInStringRes[0];
            residualLen = chunkSize - findInStringRes[1];              //length of that part pattern, that was left in last segment
            textPos += findInStringRes[2];

            if (findInStringRes[0] > 0) {                                           //save residual part of pattern in residualBuffer
                System.arraycopy(chunk, findInStringRes[1], residualBuffer, 0, residualLen);
                textPos -= residualLen;                                         //correct textPos according to residualBuffer length
            }
            System.arraycopy(blankArea, 0, chunk, startPos, chunkSize - startPos);      //clean chunk from position where text should start (pattern + delimiter are not touched)
            System.arraycopy(residualBuffer, 0, chunk, startPos, residualLen);             //copy residual part of pattern in new chunk
            textPos -= startPos;                                                    //sub pattern with delimiter from current text length
        }

        return -1;
    }

    public static int findInString (String subStr, String inputStr) throws IOException {
        char[] pattern = new char[subStr.length()];                                             //making pattern + '$' + input string like in findInFile method
        System.arraycopy(subStr.toCharArray(), 0, pattern, 0, subStr.length());

        char[] input = new char[inputStr.length() + pattern.length + 1];
        System.arraycopy(pattern, 0, input, 0, pattern.length);
        input[pattern.length] = '$';
        System.arraycopy(inputStr.toCharArray(), 0, input, pattern.length + 1, inputStr.length());

        int[] res = findSubstring(pattern, input, input.length, 0);             //returns max length of similarity with pattern, its index and position in whole string,
        int startPos = pattern.length;
        if (res[0] == pattern.length) {         //if pattern was found, return its position computed by submission of whole string and pattern + '$'
            return res[1] - startPos;
        }
        else {
            return -1;
        }
    }

    private static int[] findSubstring (char[] subStr, char[] chunk, int chunkSize, int startPos) throws IOException { //uses the same algorithm as in findInFile, but without buffers and segment incidents solving

        int textPos = 0;                                //current position in whole text => returned index if pattern was found
        int maxLen = 0;
        int maxLenInd = 0;
        int patternLen = subStr.length;
        int[] res = new int[3];

        int chunkRes[] = zFunction(chunk, chunk.length);        //zFunction result array
        int zLen;                                               //length of pattern entry in chunk for current symbol
        for (int i = 0; i < chunkRes.length; i++) {             //check results of zFunction
            zLen = chunkRes[i];
            if (zLen == patternLen) {
                res[0] = zLen;
                res[1] = maxLenInd;
                res[2] = textPos;               //if pattern was found return all counters immediately
                return res;
            }
            if (zLen > maxLen) {                            //else update maxLen if longer entry was found
                maxLen = zLen;
            }
            if (zLen == 0) {                           //if entry was chopped by foreign symbol, reset maxLen
                maxLen = 0;
                maxLenInd = i;
            }
            textPos++;                                  //count current position in whole text
        }
        maxLenInd++;

        res[0] = maxLen;                //return counters after computations
        res[1] = maxLenInd;
        res[2] = textPos;
        return res;
    }

    private static int[] zFunction(char[] input, int strLen) {   //returns array with amounts of symbols in input similar to symbols from pattern counting from beginning
        int zArr[] = new int[strLen];
        int lastCheckedSymInd = 0;                      //the index of last analysed symbol
        int longestEntryLastInd = 0;                      //the index of last symbol of current longest pattern entry

        for (int i = 1; i < strLen; i++) {                  //for all symbols in input string
            if (i <= longestEntryLastInd)                                     //initialize value of symbol with value found earlier if it is already in entry
                zArr[i] = min (longestEntryLastInd - i + 1, zArr[i - lastCheckedSymInd]);
            while (i+zArr[i] < strLen && input[zArr[i]] == input[i + zArr[i]]) {        //increases value of symbol while string is similar to pattern
                zArr[i]++;
            }
            if (i + zArr[i] - 1 > longestEntryLastInd) {          //if new entry is longer then previous, than update longestEntryLastInd with new value, and increase analysed symbols counter
                lastCheckedSymInd = i;
                longestEntryLastInd = i + zArr[i] - 1;
            }
        }

        return zArr;                    //return array of symbol values
    }

    private static int min(int a, int b) {
        if (a >= b) {
            return b;
        }
        else {
            return a;
        }
    }

}
