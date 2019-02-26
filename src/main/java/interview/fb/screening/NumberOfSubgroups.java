package interview.fb.screening;

import java.util.Arrays;
import java.util.stream.LongStream;

public class NumberOfSubgroups {

    public static long getNumberOfSubgroups(int n) {
        long nFactorial = factorial(n);
        long result = 0;
        for (int k = 0; k < n; k++) {
            result = result + nFactorial / (factorial(n - k) * factorial(k));
        }
        return result;
    }

    public static long getNumberOfGroupsForLimitedSumOfMinMaxElements(int[] array, int limit, int minIndex, int maxIndex, long result) {
        if (minIndex == maxIndex) {
            return result;
        } else {
            int currentMin = array[minIndex];
            int currentMax = array[maxIndex];

            if (currentMin + currentMax < limit) {
                result = result + 3 + getNumberOfSubgroups(maxIndex - minIndex - 2);
                return getNumberOfGroupsForLimitedSumOfMinMaxElements(array, limit, ++minIndex, maxIndex, result);
            } else {
                return getNumberOfGroupsForLimitedSumOfMinMaxElements(array, limit, minIndex, --maxIndex, result);
            }
        }

    }

    public static void main(String[] args) {
        int[] array = new int[]{7, 2, 3, 4};
        int limit = 8;
        long result = 0;
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        System.out.println(getNumberOfGroupsForLimitedSumOfMinMaxElements(array, limit, 0, array.length - 1, result));
    }

    /*
    alternative
    //    BigInteger factorial = BigIntegerMath.factorial(n);
//compile group: 'com.google.guava', name: 'guava', version: '27.0.1-jre'

     */

    public static long factorial(int n) {
        if (n > 20) throw new IllegalArgumentException(n + " is out of range");
        return LongStream.rangeClosed(1, n).reduce(1, (a, b) -> a * b);
    }

}
