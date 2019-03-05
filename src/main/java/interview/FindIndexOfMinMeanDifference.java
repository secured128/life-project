package interview;

public class FindIndexOfMinMeanDifference {


    public static void main(String[] args) {
        float[] array = new float[]{7, 2, 3, 4, 20, 15, 66,0,0,1,0,100};
        System.out.println(findIndexOfMinMeanDifference(array));
    }


    public static int findIndexOfMinMeanDifference(float[] array) {
        float currentMeanDifference = Integer.MAX_VALUE;
        int currentMeanDifferenceIndex = -1;
        float[] arrayL = new float[array.length];
        float[] arrayR = new float[array.length];
        arrayL[0] = array[0];
        arrayR[array.length - 1] = array[array.length - 1];
        for (int i = 1; i < array.length; i++) {
            arrayL[i] = ((arrayL[i - 1] * i) + array[i]) / (i + 1);
            arrayR[array.length - i - 1] = ((arrayR[array.length - i] * i) + array[array.length - i - 1]) / (i + 1);
        }

        for (int i = 1; i < array.length - 2; i++) {
            if (Math.abs(arrayL[i - 1] - arrayR[i + 1]) < currentMeanDifference) {
                currentMeanDifference = Math.abs(arrayL[i - 1] - arrayR[i + 1]);
                currentMeanDifferenceIndex = i;
            }
        }

        return currentMeanDifferenceIndex;
    }
}
