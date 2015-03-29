package com.facebook.eva.algorithm;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Eva on 21.2.2015..
 */
public class DictionaryDecode {

    private static final Hashtable<Integer, Character> DICTIONARY = new Hashtable<Integer,
            Character>() { {
        put(1, 'a');
        put(2, 'b');
        put(3, 'c');
        put(4, 'd');
        put(5, 'e');
        put(6, 'f');
        put(7, 'g');
        put(8, 'h');
        put(9, 'i');
        put(10, 'j');
        put(11, 'k');
        put(12, 'l');
        put(13, 'm');
        put(14, 'n');
        put(15, 'o');
        put(16, 'p');
        put(17, 'q');
        put(18, 'r');
        put(19, 's');
        put(20, 't');
        put(21, 'u');
        put(22, 'v');
        put(23, 'w');
        put(24, 'z');
    }
    };

    private static final String DECODING_STRING = "24123";

    public static String testDecodeString() {
        return getAllDecodedStrings().toString();
    }

    public static List<String> getAllDecodedStrings() {
        List<String> decodedStrings = new ArrayList<String>();
        decodeString(decodedStrings, "", DECODING_STRING);
        return decodedStrings;
    }

    public static int decodeString(Hashtable<Integer, Character> dictionary, String str) {
        if (str == null || str.isEmpty()) {
            return 1;
        }

        char c1 = str.charAt(0);
        int digit1 = c1 - '0';

        if (str.length() > 1) {
            char c2 = str.charAt(1);
            int digit2 = c2 - '0' + 10 * digit1;

            if (dictionary.containsKey(digit2)) {
                return decodeString(dictionary, str.substring(1, str.length())) + decodeString
                        (dictionary, str.substring(2, str.length()));
            } else {
                return decodeString(dictionary, str.substring(1, str.length()));
            }
        } else {
            return 1;
        }

    }



    public static void decodeString(List<String> decodedStrings, String decodedString, String subString) {
        if (subString == null || subString.isEmpty()) {
            decodedStrings.add(decodedString);
            return;
        }

        char c1 = subString.charAt(0);
        int digit1 = c1 - '0';
        Character decodedChar1 = DICTIONARY.get(digit1);

        decodeString(decodedStrings, decodedString + decodedChar1, subString.substring(1,
                subString.length()));

        if (subString.length() > 1) {
            char c2 = subString.charAt(1);
            int digit2 = c2 - '0' + 10 * digit1;

            if (DICTIONARY.containsKey(digit2)) {
                Character decodedChar2 = DICTIONARY.get(digit2);
                decodeString(decodedStrings, decodedString + decodedChar2, subString.substring(2,
                        subString.length()));
            }
        }
    }

}
