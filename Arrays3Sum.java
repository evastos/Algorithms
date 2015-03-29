import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eva on 19.2.2015..
 */
public class Arrays3Sum {

    /** Given an array of numbers (integers), find elements which sum up to the target sum. **/

    private static final int[] ARRAY_3_SUM = new int[] {1, 4, 4, -1, -2, -6, 100, 3, 20, -10, -7,
            0};

    private static final int SUM = 2;

    public static String test3Sum() {
        StringBuilder builder = new StringBuilder();
        for (int[] elements : find3Sum(ARRAY_3_SUM, SUM)) {
            builder.append(java.util.Arrays.toString(elements));
            builder.append(" - ");
        }

        return builder.toString();
    }

    public static List<int[]> find3Sum(int[] array, int sum) {
        List<int[]> elements = new ArrayList<int[]>();
        java.util.Arrays.sort(array);

        for (int i = 0, j = i + 1, k = array.length - 1; i < array.length - 2; i++, j = i + 1) {
            while (j < k) {
                if (array[i] > sum) {
                    break;
                }

                int tempSum = array[i] + array[j] + array[k];

                if (tempSum == sum) {
                    int[] elements3Sum = new int[3];
                    elements3Sum[0] = array[i];
                    elements3Sum[1] = array[j];
                    elements3Sum[2] = array[k];
                    elements.add(elements3Sum);
                    j++;
                } else if (tempSum < sum) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return elements;
    }
}
