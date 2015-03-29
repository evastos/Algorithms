package com.facebook.eva.algorithm;

/**
 * Created by Eva on 22.2.2015..
 */
public class BitwiseDivision {

    /* Implement division without using * or / */

    private static int bitwiseDivisionRecursive(int a, int b, int result) {
        int resultMid = 1;
        int newB = b;
        while (newB < a) {
            newB = newB << 1;
            resultMid = resultMid << 1;
        }
        if (newB > a) {
            newB = newB >> 1;
            resultMid = resultMid >> 1;
        }
        if ((a - newB) < b) {
            return result + resultMid;
        } else {
            return bitwiseDivisionRecursive(a - newB, b, result + resultMid);
        }
    }

    private static int bitwiseDivision(int a, int b) {
        int result = 0;
        int resultMid = 1;
        int newB = b;
        while(true) {
            while (newB < a) {
                newB = newB << 1;
                resultMid = resultMid << 1;
            }
            if (newB > a) {
                newB = newB >> 1;
                resultMid = resultMid >> 1;
            }
            result = result + resultMid;
            if ((a - newB) < b) {
                return result;
            }
            a = a - newB;
            newB = b;
            resultMid = 1;
        }
    }
}
