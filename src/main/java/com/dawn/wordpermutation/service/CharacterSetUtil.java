package com.dawn.wordpermutation.service;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CharacterSetUtil {

    public static String removeHebrewSpecialCharacters() throws IOException {

        File dictionaryFile =
                new File("/home/dawn/java_projects/word-permutation/src/main/resources/dict-he-temp.dat");
        countASCIILetters(dictionaryFile);

        return null;
    }

    private static void countASCIILetters(File f) throws IOException {
        try (Reader r =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(f),
                                     StandardCharsets.UTF_8))) {
            int intch;
            while ((intch = r.read()) != -1) {
                int ch = (char) intch;
                System.out.println(ch);
//                System.out.println(intch);
            }
//            return count;
        }
    }

}
