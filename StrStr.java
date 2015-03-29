package com.facebook.eva.algorithm;

/**
 * Created by Eva on 22.2.2015..
 */
public class StrStr {

    /**
     * Implement strstr() to find a substring in a string.
     */

    public static int strStr(String str, String subStr) {
        if (str == null || subStr == null || subStr.length() > str.length()) {
            return -1;
        }

        if (subStr.isEmpty()) {
            return 0;
        }

        int position = -1;
        for (int i = 0; i <= str.length() - subStr.length(); i++) {
            boolean hasSubstring = true;
            for (int j = 0; j < subStr.length(); j++) {
                if (j + i >= str.length()) {
                    hasSubstring = false;
                    break;
                }
                if (str.charAt(j + i) != subStr.charAt(j)) {
                    hasSubstring = false;
                    break;
                }
            }
            if (hasSubstring) {
                position = i;
                break;
            }
        }
        return position;
    }
}
