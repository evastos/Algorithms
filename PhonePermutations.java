import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Eva on 21.2.2015..
 */
public class PhonePermutations {

    private static final String DECODE_PHONE_STRING = "3897";

    public static String testPhonePermutations() {
        List<String> decodedPhoneStrings = new ArrayList<String>();
        decodePhoneString(decodedPhoneStrings, "", DECODE_PHONE_STRING);
        return decodedPhoneStrings.toString();
    }

    private static final Hashtable<Integer, HashSet<Character>> PHONE_DICTIONARY = new
            Hashtable<Integer,
            HashSet<Character>>() { {
        put(1, new HashSet<Character>());
        put(2, new HashSet<Character>() {{ add('a'); add('b'); add('c');}});
        put(3, new HashSet<Character>() {{ add('d'); add('e'); add('f');}});
        put(4, new HashSet<Character>() {{ add('g'); add('h'); add('i');}});
        put(5, new HashSet<Character>() {{ add('j'); add('k'); add('l');}});
        put(6, new HashSet<Character>() {{ add('m'); add('n'); add('o');}});
        put(7, new HashSet<Character>() {{ add('p'); add('q'); add('r'); add('s');}});
        put(8, new HashSet<Character>() {{ add('t'); add('u'); add('v');}});
        put(9, new HashSet<Character>() {{ add('w'); add('x'); add('y'); add('z');}});
        put(0, new HashSet<Character>() {{ add('+');}});
    }
    };

    public static void decodePhoneString(List<String> decodedStrings, String decodedString, String subString) {
        if (subString == null || subString.isEmpty()) {
            decodedStrings.add(decodedString);
            return;
        }

        char c = subString.charAt(0);
        int digit = c - '0';

        if (PHONE_DICTIONARY.containsKey(digit)) {
            HashSet<Character> decodingSet = PHONE_DICTIONARY.get(digit);
            for (char decodingChar : decodingSet) {
                decodePhoneString(decodedStrings, decodedString + decodingChar, subString.substring(1,
                        subString.length()));
            }

        }
    }
}
