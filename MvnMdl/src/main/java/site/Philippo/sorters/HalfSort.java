package site.Philippo.sorters;

import java.util.Arrays;

/**
 * <pre>
 * Sorts the array applying half-division method
 * @see Sorters
 * </pre>
 *
 * @author Larin
 */
public class HalfSort extends Sorters {

    private final Sorters sorter;

    /**
     * Constructor, taking one of sorters to use
     *
     * @param sorter Sorter, using in method
     */
    public HalfSort(Sorters sorter) {
        this.sorter = sorter;
    }

    /**
     * Sorting array with half-division method
     *
     * @param arr    The array to be sorted
     * @param minInd Start index of the interval, inclusive
     * @param maxInd End index of the interval, exclusive
     */
    @Override
    public void doSort(int[] arr, int minInd, int maxInd) {
        int[][] twoDimResultedArray = split(arr, (arr.length / 2));
        for (int[] array : twoDimResultedArray) {
            sorter.doSort(array);
        }
        System.arraycopy(merge(twoDimResultedArray[0], twoDimResultedArray[1]), 0, arr, 0, arr.length);
    }

    /**
     * Returns a sorter
     *
     * @return Sorter
     */
    public Sorters getSorter() {
        return sorter;
    }

    /**
     * Method to merge two arrays of integers
     *
     * @param array1 First part of array to merge
     * @param array2 Second part of array to merge
     * @return Merged array
     */
    private int[] merge(int[] array1, int[] array2) {

        int finalLength = array1.length + array2.length;
        int[] result = new int[finalLength];
        int a = 0;
        int b = 0;

        for (int i = 0; i < finalLength; i++) {
            if (a < array1.length && b < array2.length) {
                if (array1[a] > array2[b]) {
                    result[i] = array2[b++];
                } else {
                    result[i] = array1[a++];
                }
            } else if (a < array1.length) {
                result[i] = array1[a++];
            } else {
                result[i] = array2[b++];
            }
        }
        return result;
    }

    /**
     * Method to divide array of integers, returns two-dimensional array
     *
     * @param array Array to split
     * @param i     Index of array, where to divide (middle is the best option)
     * @return Two-dimensional array
     */
    private int[][] split(int[] array, int i) {
        int[][] twoDimResultedArray = new int[2][];
        twoDimResultedArray[0] = Arrays.copyOfRange(array, 0, i);
        twoDimResultedArray[1] = Arrays.copyOfRange(array, i, array.length);
        return twoDimResultedArray;
    }

    @Override
    public String toString() {
        return "Half division with " + sorter.toString();
    }
}