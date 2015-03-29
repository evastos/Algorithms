package com.facebook.eva.algorithm;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Eva on 15.2.2015..
 */
public class RandomTriplet {

    private static final String FROM_STRING = "abcdefghijklmnoprstuvz";

    public static String guessString() {
        return guessString(FROM_STRING.length());
    }

    private static String getRandomTriplet() {
        return getRandomTriplet(FROM_STRING);
    }

    static class CharPair extends Pair<Character, Integer> {
        public CharPair(char c, int i) {
            super(c, i);
        }

        public Character getChar() {
            return first;
        }

        public Integer getValue() {
            return second;
        }

    }

    private static String guessString(int length) {
        char[] stringArr = new char[length];
        for (int i = 0; i < length; i++) {
            stringArr[i] = ' ';
        }
        int idxStr = 0;

        while (idxStr < length) {
            List<CharPair> pairs = new ArrayList<CharPair>();

            while (pairs.size() < length - idxStr) {
                final String triplet = getRandomTriplet();
                for (int i = 0, value = 1; i < triplet.length(); i++) {
                    final char tripletChar = triplet.charAt(i);

                    CharPair charPair = new CharPair(tripletChar,
                            value);
                    int countOfCharInTriplet = countOfCharInTriplet(triplet, tripletChar);
                    int countOfCharInPairs = countOfChar(pairs, tripletChar);
                    int countOfCharInString = countOfCharInString(stringArr, tripletChar);

                    if (countOfCharInTriplet > countOfCharInPairs + countOfCharInString) {
                        pairs.add(charPair);
                    }

                    if (countOfCharInString >= 1) {
                        continue;
                    }

                    updateFirstCharPair(pairs, charPair);
                    value++;
                }
            }

            while (pairs.size() > 1) {
                final String triplet = getRandomTriplet();
                for (int i = 0, value = 1; i < triplet.length(); i++) {
                    final char tripletChar = triplet.charAt(i);

                    int countOfCharInString = countOfCharInString(stringArr, tripletChar);
                   if (countOfCharInString >= 1) {
                       continue;
                    }
                    CharPair charPair = new CharPair(tripletChar,
                            value);
                    updateFirstCharPair(pairs, charPair);
                    value++;
                }

                for (int i = 0; i < pairs.size(); i++) {
                    CharPair p = pairs.get(i);
                    if (p.getValue() > 1) {
                        pairs.remove(i);
                    }
                }
            }

            stringArr[idxStr] = pairs.get(0).getChar();
            idxStr++;
        }

        return new String(stringArr);
    }

    private static int countOfCharInString(char[] arr, char c) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c) {
                count++;
            }
        }
        return count;
    }

    private static void updateFirstCharPair(List<CharPair> pairs, CharPair charPair) {
        for (int j = 0; j < pairs.size(); j++) {
            CharPair pair = pairs.get(j);
            if (pair.getChar() == charPair.getChar()) {
                if (pair.getValue() < charPair.getValue()) {
                    pairs.set(j, charPair);
                }
                break;
            }
        }
    }


    private static int countOfCharInTriplet(String triplet, char c) {
        int count = 0;
        for (int i = 0; i < triplet.length(); i++) {
            if (triplet.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    private static int countOfChar(List<CharPair> pairs, char c) {
        int count = 0;
        for (CharPair p : pairs) {
            if (p.getChar() == c) {
                count++;
            }
        }
        return count;
    }




    private static String getRandomTriplet(String fromString) {
        if (fromString == null) {
            throw new IllegalArgumentException("Input parameter must not be null");
        }

        fromString = fromString.toLowerCase();
        if (fromString.length() < 3) {
            throw new IllegalArgumentException("Argument is too short to produce triplet: " + fromString);
        }

        int[] indexArr = new int[3];
        HashSet<Integer> integers = new HashSet<Integer>();
        for (int i = 0; i < 3; i++) {
            int rand = getRandomIndex(fromString.length());
            while (integers.contains(rand)) {
                rand = getRandomIndex(fromString.length());
            }
            integers.add(rand);
            indexArr[i] = rand;
        }
        Arrays.sort(indexArr);

        StringBuilder builder = new StringBuilder();
        builder.append(fromString.charAt(indexArr[0])).append(fromString.charAt(indexArr[1]))
                .append(fromString.charAt(indexArr[2]));
        return builder.toString();

    }

    private static int getRandomIndex(int length) {
        return (int) (Math.random() * length);
    }


}
