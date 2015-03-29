import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Eva on 22.2.2015..
 */
public class Anagrams {

    /**
     * Given an array of strings, return a list of grouped anagrams.
     */

    private static final String[] STRINGS = new String[]{"star", "astr", "car", "rac", "st"};

    public static String testGroupAnagrams() {
        return groupAnagrams(STRINGS).toString();
    }

    public static List<List<String>> groupAnagrams(String[] strings) {
        HashMap<String, List<String>> result = new HashMap<String, List<String>>();

        for (int i = 0; i < strings.length; i++) {
            final String str = strings[i];
            String sortedStr = sortString(str);
            if (result.containsKey(sortedStr)) {
                List<String> anagrams = result.get(sortedStr);
                anagrams.add(str);
                result.put(sortedStr, anagrams);
            } else {
                result.put(sortedStr, new ArrayList() {{
                    add(str);
                }});
            }
        }

        List<List<String>> groupedAnagrams = new ArrayList<List<String>>();

        for (String key : result.keySet()) {
            groupedAnagrams.add(result.get(key));
        }
        return groupedAnagrams;

    }

    private static String sortString(String s) {
        char[] chars = s.toCharArray();
        java.util.Arrays.sort(chars);
        String sorted = new String(chars);
        return sorted;
    }
}
