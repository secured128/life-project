package interview.spiral;

public class Spiral {

    static void fillSpiralLevel(int[][] array, int levelIndex, int currentNumber) {
        int maxNumber = array.length * array.length;
        int sideLength = array.length - levelIndex;
        if (currentNumber == maxNumber) {
            return;
        }
        if (currentNumber + 1 == maxNumber) {
            array[levelIndex][levelIndex] = ++currentNumber;
            return;
        } else {
            for (int i = levelIndex; i < sideLength; i++) {
                array[levelIndex][i]=++currentNumber;
            }
            for (int j = levelIndex+1; j < sideLength; j++) {
                array[j][sideLength-1]=++currentNumber;
            }
            for (int k = sideLength-2; k >= levelIndex; k--) {
                array[sideLength-1][k]=++currentNumber;
            }
            for (int l = sideLength-2; l > levelIndex; l--) {
                array[l][levelIndex]=++currentNumber;
            }
            fillSpiralLevel(array, ++levelIndex, currentNumber);
        }

    }

    static int[][] spiral(int n) {
        int[][] array = new int[n][n];
        fillSpiralLevel(array, 0, 0);
        return array;
    }

    public static void main(String[] args) {
        int n = 11;
        int[][] array = spiral(n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println();
        }
    }


}
