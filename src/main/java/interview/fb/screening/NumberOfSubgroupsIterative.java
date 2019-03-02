package interview.fb.screening;

import java.util.Arrays;

public class NumberOfSubgroupsIterative {


    public static double getNumberOfUnicGroupsForLimitedSumOfMinMaxElements(int[] array, int limit) {

        double result = 0;
        int minIndex = 0;
        int maxIndex = array.length - 1;
        while (minIndex <= maxIndex) {

            if ((array[maxIndex] * 2 < limit)) {
                result = result + Math.pow(2, maxIndex - minIndex + 1);
                return result;
            }

            if (array[minIndex] + array[maxIndex] < limit) {
                result = result + (minIndex < maxIndex ? 2 : 0) + ((maxIndex - minIndex - 1) > 0 ? Math.pow(2, (maxIndex - minIndex - 1)) : 0)
                ;
                minIndex++;
            } else {
                maxIndex--;

            }
        }

        return result;

    }

    /**
     * Input {7, 2, 3, 4}
     * Expected result : //{2},{3},{2,3,4},{2,3},{3,4},{2,4} = 6
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] array = new int[]{7, 2, 3, 4};
        Arrays.sort(array);
        System.out.println(array);
        System.out.println(getNumberOfUnicGroupsForLimitedSumOfMinMaxElements(array, 8));
    }
}
