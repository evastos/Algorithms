package com.facebook.eva.algorithm;

/**
 * Created by Eva on 21.2.2015..
 */
public class SquareRoot {

    /* Binary search for square root */

    public static int squareRoot(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 0) {
            return -1;
        }

        int start = 0;
        int end = x;

        int sqrt = (start + end) / 2;

        while(sqrt * sqrt != x && end - start > 1) {
            if (sqrt * sqrt > x) {
                end = (end + start) / 2;
            } else {
                start = sqrt;
            }
            sqrt = (start + end) / 2;
        }
        return sqrt;
    }

}
