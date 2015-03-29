import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Eva on 18.2.2015..
 */
public class Arrays {

    /**
     * Remove duplicate characters from an array.
     */

    public static final char[] DUPLICATES_ARRAY = "banadnaszonanas".toCharArray();

    public static String testRemoveDuplicates() {
        return new String(removeDuplicates(DUPLICATES_ARRAY));
    }

    public static char[] removeDuplicates(char[] arr) {
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < arr.length; i++) {
            char c = arr[i];
            if (set.contains(c)) {
                arr[i] = ' ';
            } else {
                set.add(c);
            }
        }

        int emptyIdx = 0, swapIdx = 0;
        while (emptyIdx < arr.length && swapIdx < arr.length) {
            while (arr[emptyIdx] != ' ') {
                emptyIdx++;
            }
            swapIdx = emptyIdx;
            while (swapIdx < arr.length && arr[swapIdx] == ' ') {
                swapIdx++;
            }
            swapElements(arr, emptyIdx, swapIdx);
        }
        return arr;

    }

    private static void swapElements(char[] arr, int i, int j) {
        if (i < 0 || i >= arr.length || j < 0 || j >= arr.length) {
            return;
        }
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Merge sorted arrays. Assume one of the arrays has the size of both arrays combined.
     */

    private static final char[] ARRAY_B = new char[]{'1', '3', '4', '_', '_', '_',
            '_'};
    private static final char[] ARRAY_A = new char[]{'2', '3', '6', '8'};

    public static String testMergeSortedArrays() {
        return java.util.Arrays.toString(mergeSortedArrays(ARRAY_B, ARRAY_A));
    }

    public static char[] mergeSortedArrays(char[] arrA, char[] arrB) {
        // arrB should be the longer one
        if (arrA.length > arrB.length) {
            return mergeSortedArrays(arrB, arrA);
        }

        int idxA = 0, idxB = 0;
        while (idxB < arrB.length) {
            if (idxA < arrA.length) {
                if (arrB[idxB] == '_') {
                    arrB[idxB] = arrA[idxA];
                    idxA++;
                } else if (arrA[idxA] < arrB[idxB]) {
                    insert(arrB, idxB, arrA[idxA]);
                    idxA++;
                }

            }
            idxB++;
        }
        return arrB;
    }

    private static void insert(char[] arr, int idx, char element) {
        if (idx >= arr.length || idx < 0) {
            return;
        }
        char nextSwap = element;
        for (int i = idx; i < arr.length; i++) {
            char temp = arr[i];
            arr[i] = nextSwap;
            nextSwap = temp;
        }
    }

    /**
     * Remove Bs duplicate As from an array with no additional space complexity.
     */

    private static final String AB_ARRAY = "aagaa22312aaasbbb4bb6bb";

    public static String testRemoveBDuplicateA() {
        return new String(removeBDuplicateA(AB_ARRAY.toCharArray()));
    }

    public static char[] removeBDuplicateA(char[] arr) {
        int a = 0;
        int b = 0;

        while (a < arr.length && b < arr.length) {
            while (a < arr.length && arr[a] != 'a') {
                a++;
            }
            while (b < arr.length && arr[b] != 'b') {
                b++;
            }

            if (a < arr.length && b < arr.length) {
                insertShift(arr, a, b, 'a');
                if (a < b) {
                    a = a + 2;
                } else {
                    a++;
                }
                b++;
            }
        }
        return arr;
    }


    private static void insertShift(char[] arr, int start, int end, char element) {
        if (start >= arr.length || start < 0 || end >= arr.length || end < 0) {
            return;
        }
        char nextSwap = element;
        if (start <= end) {
            for (int i = start; i <= end; i++) {
                char temp = arr[i];
                arr[i] = nextSwap;
                nextSwap = temp;
            }
        } else {
            for (int i = start; i >= end; i--) {
                char temp = arr[i];
                arr[i] = nextSwap;
                nextSwap = temp;
            }
        }
    }

    /**
     * Given an array of integers, group elements which sum up to the same value.
     */

    public static void printSums(int[] arr) {
        HashMap<Integer, ArrayList<Pair<Integer, Integer>>> sumMap = new HashMap<Integer, ArrayList<Pair<Integer, Integer>>>();

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                Pair<Integer, Integer> pair = new Pair<Integer, Integer>(i, j);
                int sum = arr[i] + arr[j];
                if (sumMap.containsKey(sum)) {
                    sumMap.get(sum).add(pair);
                } else {
                    ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<Pair<Integer, Integer>>();
                    pairs.add(pair);
                    sumMap.put(sum, pairs);
                }
            }
        }

        for (Integer sum : sumMap.keySet()) {
            ArrayList<Pair<Integer, Integer>> pairs = sumMap.get(sum);
            if (pairs.size() > 1) {
                printPairs(sum, pairs);
            }
        }
    }

    private static void printPairs(int sum, ArrayList<Pair<Integer, Integer>> pairs) {
        StringBuilder builder = new StringBuilder("(");
        for (Pair<Integer, Integer> pair : pairs) {
            builder.append(pair.first).append(", ").append(pair.second).append(", ");
        }

        builder.deleteCharAt(builder.length() - 1);
        builder.deleteCharAt(builder.length() - 1);

        builder.append(")");

        System.out.format("Pairs for sum %d are: %s", sum, builder.toString());
    }

    /**
     * Given an array of ages (integers) sorted lowest to highest, output the number of
     * occurrences for each age.
     * http://www.careercup.com/question?id=5129701993480192
     */

    public static void printNumberOfAges(int[] ages) {
        int maxAge = ages[ages.length - 1];
        int minAge = ages[0];

        int[] count = new int[maxAge - minAge + 1];

        for (int i = 0; i < count.length; i++) {
            count[i] = 0;
        }

        for (int i = 0; i < count.length; i++) {
            int countAge = getCount(ages, 0, ages.length - 1, i + minAge);
            count[i] = countAge;

        }
    }

    private static int getCount(int[] ages, int start, int end, int age) {

        if (ages[start] == ages[end] && ages[start] == age) {
            return end - start + 1;
        }

        if (ages[end] < age || ages[start] > age) {
            return 0;
        }

        int middle = (start + end) / 2;

        if (ages[middle] > age) {
            return getCount(ages, start, middle - 1, age);
        } else if (ages[middle] < age) {
            return getCount(ages, middle + 1, end, age);
        } else {
            return getCount(ages, start, middle - 1, age) + getCount(ages, middle + 1, end, age) + 1;
        }
    }
}
