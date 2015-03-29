package com.facebook.eva.algorithm;

/**
 * Created by Eva on 14.2.2015..
 */
public class Palindromes {

    public static boolean isKPalindrome(String s, int k) {
        if (s.length() <= 1) {
            return true;
        }
        if (k > 0 && isPalindrome(s)) {
            return true;
        }
        if (k == 0) {
            return isPalindrome(s);
        }

        int begin = 0;
        int end = s.length() - 1;

        while(begin < end && s.charAt(begin) == s.charAt(end)) {
            begin++;
            end--;
        }

        return isKPalindrome(s.substring(begin + 1, end + 1), k - 1) || isKPalindrome(s.substring(begin, end), k - 1);
    }

    public static boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        int begin = 0, end = s.length() - 1;
        while (begin < end) {
            if (s.charAt(begin) != s.charAt(end)) {
                return false;
            }
            begin++;
            end--;
        }
        return true;
    }
}
