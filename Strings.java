import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Eva on 14.2.2015..
 */
public class Strings {

    /**
     * Reverse a char array with no additional space complexity.
     */

    public static void inplaceReverse(char[] arr, int length) {
        if (arr == null || arr.length == 0) {
            return;
        }

        reverse(arr, 0, arr.length - 1);

        int wordBegin = 0, wordEnd = wordBegin;

        while (wordBegin < arr.length && !(Character.isLetter(arr[wordBegin]) || Character.isDigit(arr[wordBegin]))) {
            wordBegin++;
        }

        while (wordEnd < arr.length) {
            while (wordEnd < arr.length && arr[wordEnd] != ' ') {
                wordEnd++;
            }

            if (wordEnd >= arr.length) {
                break;
            }

            reverse(arr, wordBegin, wordEnd - 1);
            wordBegin = wordEnd + 1;
            wordEnd = wordBegin;
        }

        reverse(arr, wordBegin, arr.length - 1);
    }

    public static void reverse(char[] arr, int begin, int end) {
        if (begin < 0 || end > arr.length - 1) {
            return;
        }

        while (begin < end) {
            char temp = arr[begin];
            arr[begin] = arr[end];
            arr[end] = temp;
            begin++;
            end--;
        }
    }

    /**
     * Given two strings, check if they are one edit apart.
     * Edit = delete, insert or replace.
     */

    public static boolean isOneEditApart(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }

        boolean isEdited = false;
        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        } else if (s1.length() == s2.length()) {
            int idx = 0;
            while (idx < s1.length()) {
                if (s1.charAt(idx) != s2.charAt(idx)) {
                    if (isEdited) {
                        return false;
                    } else {
                        isEdited = true;
                    }
                }
                idx++;
            }
            if (isEdited) {
                return true;
            } else {
                return false;
            }

        } else {
            for (int idx1 = 0, idx2 = 0; idx1 < s1.length() && idx2 < s2.length(); ) {
                if (s1.charAt(idx1) == s2.charAt(idx2)) {
                    idx1++;
                    idx2++;
                } else {
                    if (isEdited) {
                        return false;
                    } else {
                        if (s1.length() < s2.length()) {
                            idx2++;
                        } else {
                            idx1++;
                        }
                    }
                }
            }

            if (!isEdited) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * Given a string and a set of characters, find minimum substring which contains all the
     * characters from the set.
     */

    private static final String MY_STRING = "ADABACOBECODEBANC";
    private static final Character[] SET_VALUES = new Character[] { 'B', 'C', 'O' };
    private static final Set<Character> MY_SET = new HashSet<Character>(Arrays.asList(SET_VALUES));

    public static String testFindMinSubstring() {
        return findMinSubstring2(MY_STRING, MY_SET);
    }

    public static String findMinSubstring(String s, Set<Character> set) {
        if (s == null || s.isEmpty() || s.length() < set.size()) {
            return "";
        }

        HashSet<Character> countSet = new HashSet<Character>();
        int minLen = set.size();

        while(minLen <= s.length()) {
            for (int i = 0; i <= s.length() - minLen; i++) {
                String substr = s.substring(i, i + minLen);
                int countChars = 0;
                countSet.clear();
                for (int j = 0; j < substr.length(); j++) {
                    char c = substr.charAt(j);
                    if (set.contains(c) && !countSet.contains(c)) {
                        countSet.add(c);
                        countChars++;
                    }
                }
                if (countChars >= set.size()) {
                    return substr;
                }
            }
            minLen++;
        }
        return "";
    }

    public static String findMinSubstring2(String s, Set<Character> set) {
        if (s == null || s.isEmpty() || set.size() > s.length()) {
            return "";
        }

        Set<Character> foundChars = new HashSet<Character>();

        String substring = "";

        int start = 0, end, i = 0;
        while(i < s.length()) {
            char c = s.charAt(i);

            if (set.contains(c)) {
                if (!foundChars.contains(c) ) {
                    foundChars.add(c);
                    if (foundChars.size() == 0) {
                        start = i;
                    }
                } else if (foundChars.size() == 1) {
                    start = i;
                }
            }

            if (foundChars.size() == set.size()) {
                end = i + 1;
                if (substring.equals("") || substring.length() > end - start) {
                    substring = s.substring(start, end);
                }

                if (substring.length() == set.size()) {
                    return substring;
                }

                foundChars.clear();
                start++;
                i = start;
                continue;
            }
            i++;

        }
        return substring;
    }

    /**
     * Check if given strings are anagrams.
     */

    private static final String ANAGRAM1 = "hahahahahhahahahahahhaha";
    private static final String ANAGRAM2 = "hahhahahahahahahahahahah";

    public static String testIsAnagram() {
        return ANAGRAM1 + " & " + ANAGRAM2 + " are anagrams: " + String.valueOf(isAnagram(ANAGRAM1,
                ANAGRAM2));
    }

    public static boolean isAnagram(String s1, String s2) {
        if (s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()) {
            return false;
        }

        if (s1.length() != s2.length()) {
            return false;
        }

        HashMap<Character, Integer> charsMap = new HashMap<Character, Integer>();

        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            int count;
            if (charsMap.containsKey(c)) {
                count = charsMap.get(c) + 1;
            } else {
                count = 1;
            }
            charsMap.put(c, count);
        }

        for (int j = 0; j < s2.length(); j++) {
            char c = s2.charAt(j);
            int count;
            if (charsMap.containsKey(c)) {
                count = charsMap.get(c) - 1;
                if (count <= 0) {
                    charsMap.remove(c);
                } else {
                    charsMap.put(c, count);
                }
            }
        }

        if (charsMap.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Given a string, return a set of permutations for that string.
     */

    public static HashSet<String> getStringPermutations(String str) {
        HashSet<String> permutations = new HashSet<String>();
        getPermutation(permutations, "", str);
        return permutations;
    }

    public static void getPermutation(HashSet<String> permutations,
                                        String permutedString, String str) {
        if (str == null) {
            return;
        }
        if (str.isEmpty()) {
            if (!permutations.contains(permutedString)) {
                permutations.add(permutedString);
            }
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            getPermutation(permutations, permutedString + str.charAt(i), str.substring(0,
                    i) + str.substring(i+1, str.length()));
        }
    }

}
