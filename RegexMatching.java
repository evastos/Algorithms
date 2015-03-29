package com.facebook.eva.algorithm;

/**
 * Created by Eva on 21.2.2015..
 */
public class RegexMatching {

    /* Regex matcher; Regular expression checker, given a string and a regex, consider 'a'-'z','.', and '*' */

    private static final char DOT = '.'; // single char
    private static final char STAR = '*'; // repeat 0..N

//    "a.*b*bc."

    private static final String TEST_STRING = "aabhgdskj";
    private static final String REGEX_STRING = "aabhdskjz*j*u*c*";

    public static String testRegexMatcher() {
        StringBuilder builder = new StringBuilder();
        builder.append("String '").append(TEST_STRING).append("' matches regex '").append
                (REGEX_STRING).append("' == ");
        builder.append(isMatch(TEST_STRING, REGEX_STRING));
        return builder.toString();
    }

    public static boolean isMatch(String str, String regex) {
        if (str == null || regex == null) {
            return false;
        }

        if (regex.isEmpty()) {
            return str.isEmpty();
        } else if (str.isEmpty()) {
            if (regex.length() > 1 && regex.charAt(1) == STAR) {
                return true;
            }
            return false;
        }

        char stringChar = str.charAt(0);
        char regexChar = regex.charAt(0);

        if (Character.isLetter(regexChar)) {
            if (regex.length() > 1 && regex.charAt(1) == STAR) {
                    boolean isMatch = false;
                    for (int i = 0; i < str.length(); i++) {
                        if (regexChar == str.charAt(i)) {
                            isMatch = isMatch || isMatch(str.substring(i + 1, str.length()), regex.substring(2, regex.length())) || isMatch(str.substring(i, str.length()),
                                    regex.substring(2, regex.length()));
                        } else {
                            isMatch = isMatch || isMatch(str.substring(i, str.length()),
                                    regex.substring(2, regex.length()));
                        }

                    }
                    return isMatch;
            } else {
                if (stringChar == regexChar) {
                    return isMatch(str.substring(1, str.length()), regex.substring(1, regex.length()));
                } else {
                    return false;
                }
            }
        } else if (regexChar == DOT) {
            return isMatch(str.substring(1, str.length()), regex.substring(1, regex.length()));
        } else {
            return false;
        }
    }
}
